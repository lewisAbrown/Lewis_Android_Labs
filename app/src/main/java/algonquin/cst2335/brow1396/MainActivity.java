package algonquin.cst2335.brow1396;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Loads XML file
        setContentView(R.layout.activity_main);
        Log.w( "MainActivity", "In onCreate() - Loading Widgets" );
    }

    //activity is visible but not responding to touch
    @Override
    protected void onStart() {
        super.onStart();
    }

    //activity is now visible and responds to user input
    @Override
    protected void onResume() {
        super.onResume();
    }

    //activity is visible but not responding to touch
    @Override
    protected void onPause() {
        super.onPause();
    }

    //activity is no longer visible
    @Override
    protected void onStop() {
        super.onStop();
    }

    //the garbage collector is now clearing memory
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}