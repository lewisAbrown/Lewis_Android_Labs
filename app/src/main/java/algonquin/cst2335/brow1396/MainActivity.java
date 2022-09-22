package algonquin.cst2335.brow1396;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import algonquin.cst2335.brow1396.data.MainViewModel;
import algonquin.cst2335.brow1396.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding variableBinding;
    MainViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView( variableBinding.getRoot());
        variableBinding.myedittext.setText("I'm an edit text");
        variableBinding.button.setText("I'm a Button");

        variableBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editString = variableBinding.myedittext.getText().toString();
                variableBinding.myedittext.setText("Your edit text has changed ");
                variableBinding.textview.setText("My text view has changed: " + editString);
            }
        }
        );

        variableBinding.checkBox.setOnCheckedChangeListener((button, isOn) -> {
            model.isOn.postValue(true);
        });
        variableBinding.switch1.setOnCheckedChangeListener((button, isOn) -> {
            model.isOn.postValue(true);
        });
        variableBinding.radioButton.setOnCheckedChangeListener((button, isOn) -> {
            model.isOn.postValue(true);
        });

        model = new ViewModelProvider(this).get(MainViewModel.class);

        model.isOn.observe(this, selected -> {
           variableBinding.checkBox.setChecked(selected);
           variableBinding.radioButton.setChecked(selected);
           variableBinding.switch1.setChecked(selected);
           //Toast.makeText(MainActivity.this, "The value is now: " + model.isOn, Toast.LENGTH_SHORT).show();
        });


    }
}