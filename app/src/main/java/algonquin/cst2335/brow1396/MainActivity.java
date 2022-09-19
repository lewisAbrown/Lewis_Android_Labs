package algonquin.cst2335.brow1396;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //load the first text view
        TextView firstText = findViewById(R.id.firstString); // either returns a text view or a null if firstString not on the page
        firstText.setText("Java changed this");

    }
}