package algonquin.cst2335.brow1396;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import algonquin.cst2335.brow1396.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding variableBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater()); // this will load my pre-made variable from ViewBinding

        variableBinding.firstString.setText("Java changed this");

        variableBinding.secondString.setText("Im an edit text");

        setContentView(variableBinding.getRoot()); //this shows the objects on screen

        // event listener for when the button is clicked
        variableBinding.button.setOnClickListener( (view) -> {
            String Return = variableBinding.secondString.getText().toString(); //set variable return to hold the new input in text box
            variableBinding.secondString.setText("Your edit text has: " + Return); //set second text box to variable holder and return
        });


}}