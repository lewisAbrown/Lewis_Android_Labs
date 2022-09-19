package algonquin.cst2335.brow1396;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //load the first text view
        TextView firstText = findViewById(R.id.firstString); // either returns a text view or a null if firstString not on the page
        firstText.setText("Java changed this");

        EditText second = findViewById(R.id.secondString);
        second.setText("I'm an edit text"); //Java change text at launch

        String holder = "Your edit text has:"; //Place holder for onclick
        Button button = findViewById(R.id.button); //link button to java

        // event listener for when the button is clicked
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String Return = second.getText().toString(); //set variable return to hold the new input in text box
                second.setText(holder + Return); //set second text box to variable holder and return
            }
        });
}}