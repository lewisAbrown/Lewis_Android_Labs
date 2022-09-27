package algonquin.cst2335.brow1396;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import algonquin.cst2335.brow1396.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding variableBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(variableBinding.getRoot());

    }
}