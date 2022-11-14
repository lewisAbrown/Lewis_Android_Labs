package algonquin.cst2335.brow1396;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import algonquin.cst2335.brow1396.databinding.ActivityChatRoomBinding;
import algonquin.cst2335.brow1396.databinding.ReceiveMessageBinding;
import algonquin.cst2335.brow1396.databinding.SentMessageBinding;

public class ChatRoom extends AppCompatActivity {

    ArrayList<ChatMessage> messages = new ArrayList<>();

    /**
     * inner Class MyRowHolder
     */
    class MyRowHolder extends RecyclerView.ViewHolder {
        TextView messageText;
        TextView timeText;

        public MyRowHolder(@NonNull View itemView) {
            super(itemView);

            //click anywhere on the view
            itemView.setOnClickListener(click -> {
                //which row was clicked
                int position = getAbsoluteAdapterPosition();
                ChatMessage thisMessage = messages.get(position);

                //Alert Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder( ChatRoom.this );
                builder.setMessage("Do you want to delete the message: " + messageText.getText());

                builder.setTitle("Question");
                builder.setNegativeButton("No", (dialog, cl) ->{});
                builder.setPositiveButton("Yes", (dialog, cl) -> {
                    ChatMessage removedMessage = messages.get(position);

                    Executor thread = Executors.newSingleThreadExecutor();
                    thread.execute(() ->{
                        mDAO.deleteMessage(thisMessage);
                    });

                    myAdapter.notifyItemRemoved(position);
                    chatModel.messages.getValue().remove(position);

                    Snackbar.make(messageText, "you deleted message #"+ position, Snackbar.LENGTH_LONG)
                            .setAction("Undo", clk ->{
                                Executor thread2 = Executors.newSingleThreadExecutor();
                                thread2.execute(() ->{
                                    mDAO.insertMessage(thisMessage);
                                });

                                messages.add(position,removedMessage);
                                myAdapter.notifyItemInserted(position);
                            })
                            .show();
                });
                builder.create().show();
            });
            messageText = itemView.findViewById(R.id.messageText);
            timeText = itemView.findViewById(R.id.timeText);
        }
    }

    /**
     * variables and constructor
     */
    ActivityChatRoomBinding binding;
    ChatRoomViewModel chatModel ;
    private RecyclerView.Adapter myAdapter;
    ChatMessageDAO mDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //load from the database
        MessageDatabase db = Room.databaseBuilder(getApplicationContext(), MessageDatabase.class, "MessageDatabase").build();
        mDAO = db.cmDAO();

        /**
         * Variables
         */
        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
        messages = chatModel.messages.getValue();

        if(messages == null)
        {
            chatModel.messages.postValue( messages = new ArrayList<ChatMessage>());

            //load everything
            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute( () -> {
                messages.addAll(mDAO.getAllMessages());
                runOnUiThread( ()-> binding.recycleView.setAdapter(myAdapter));;
            });
        }

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));    // saying its a vertical list

        // Send on Click listener
        binding.sendButton.setOnClickListener(click -> {
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());

            ChatMessage newMessage = new ChatMessage();
            newMessage.setMessage( binding.textInput.getText().toString() );
            newMessage.setTime(currentDateandTime);
            newMessage.setBoolean(true);
            chatModel.messages.getValue().add(newMessage);

            myAdapter.notifyItemInserted( messages.size()-1 );
            //clear the previous text:
            binding.textInput.setText("");

            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute( () -> {
                mDAO.insertMessage(newMessage);
            });
        });

        //Receive on click listener
        binding.receiveButton.setOnClickListener(click -> {
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());

            ChatMessage newMessage = new ChatMessage();
            newMessage.setMessage( binding.textInput.getText().toString() );
            newMessage.setTime(currentDateandTime);
            newMessage.setBoolean(false);
            chatModel.messages.getValue().add(newMessage);

            myAdapter.notifyItemInserted( messages.size()-1 );
            //clear the previous text:
            binding.textInput.setText("");

            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute( () -> {
                mDAO.insertMessage(newMessage);
            });
        });


        myAdapter = new RecyclerView.Adapter<MyRowHolder>(){
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

                // code taken from prof
                if(viewType == 0){
                    SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater());
                    return new MyRowHolder(binding.getRoot());
                }
                else{
                    ReceiveMessageBinding binding = ReceiveMessageBinding.inflate(getLayoutInflater());
                    return new MyRowHolder(binding.getRoot());
                }

            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position){
                String text = messages.get(position).getMessage();
                holder.messageText.setText(text);

                String time = messages.get(position).getTimeSent();
                holder.timeText.setText(time);
            }

            @Override
            public int getItemCount(){
                return messages.size();
            }

            @Override
            public int getItemViewType(int position){
                if (messages.get(position).getIsSentButton() == true){
                    return 0;
                }
                else return 1;
            }

        };
    }
}