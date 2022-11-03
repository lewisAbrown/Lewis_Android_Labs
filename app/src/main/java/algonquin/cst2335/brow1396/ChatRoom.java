package algonquin.cst2335.brow1396;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import algonquin.cst2335.brow1396.databinding.ActivityChatRoomBinding;

public class ChatRoom extends AppCompatActivity {

    class MyRowHolder extends RecyclerView.ViewHolder {
        public MyRowHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    ActivityChatRoomBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recycleView.setAdapter(new RecyclerView.Adapter<MyRowHolder>(){
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
                return null;
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position){

            }

            @Override
            public int getItemCount(){
                return 0;
            }
        } );
    }
}