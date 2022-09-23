package algonquin.cst2335.brow1396;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.view.View;
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

        model = new ViewModelProvider(this).get(MainViewModel.class);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView( variableBinding.getRoot());
        variableBinding.myedittext.setText("I'm an edit text");
        variableBinding.button.setText("I'm a Button");

        model.editString.observe(this, new_string -> {
            variableBinding.myedittext.setText("Your edit text has changed ");
            variableBinding.textview.setText("My text view has changed: " + new_string);
        });

            variableBinding.button.setOnClickListener(view -> {
            model.editString.setValue(variableBinding.myedittext.getText().toString());
        }
        );

        variableBinding.checkBox.setOnCheckedChangeListener((button, isChecked) -> {
            model.isOn.postValue(isChecked);
        });
        variableBinding.switch1.setOnCheckedChangeListener((button, isChecked) -> {
            model.isOn.postValue(isChecked);
        });
        variableBinding.radioButton.setOnCheckedChangeListener((button, isChecked) -> {
            model.isOn.postValue(isChecked);
        });

        model.isOn.observe(this, selected -> {
           variableBinding.checkBox.setChecked(selected);
           variableBinding.radioButton.setChecked(selected);
           variableBinding.switch1.setChecked(selected);
           String value = model.isOn.getValue().toString();
           Toast.makeText(MainActivity.this, "The value is now: " + value, Toast.LENGTH_SHORT).show();
        });

        variableBinding.myimagebutton.setOnClickListener((button) -> {
            int width = variableBinding.myimagebutton.getWidth();
            int height = variableBinding.myimagebutton.getHeight();
            Toast.makeText(MainActivity.this, "The width = " + width + " and height = " + height, Toast.LENGTH_LONG).show();
        });

    }
}