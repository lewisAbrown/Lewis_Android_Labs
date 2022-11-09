package algonquin.cst2335.brow1396;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Variables
         */
        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
        messages = chatModel.messages.getValue();
        if(messages == null)
        {
            chatModel.messages.postValue( messages = new ArrayList<ChatMessage>());
        }

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));    // saying its a vertical list

        // On Click function
        binding.sendButton.setOnClickListener(click -> {

           SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
           String currentDateandTime = sdf.format(new Date());
           String messageText = binding.textInput.getText().toString();
           messages.add (new ChatMessage(messageText, currentDateandTime, true));

           //refresh the list
           myAdapter.notifyItemInserted(messages.size()-1); //wants to know which position has changed
           binding.textInput.setText(""); //remove what was there
        });

        binding.receiveButton.setOnClickListener(click -> {

            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());
            String messageText = binding.textInput.getText().toString();
            messages.add (new ChatMessage(messageText, currentDateandTime, false));

            //refresh the list
            myAdapter.notifyItemInserted(messages.size()-1); //wants to know which position has changed
            binding.textInput.setText(""); //remove what was there
        });


        binding.recycleView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>(){
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

        } );
    }
}