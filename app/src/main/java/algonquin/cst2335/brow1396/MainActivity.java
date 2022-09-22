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

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView( variableBinding.getRoot());
        variableBinding.myedittext.setText("I'm an edit text");
        variableBinding.button.setText("I'm a Button");

        variableBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.editString.setValue(variableBinding.myedittext.getText().toString());
                variableBinding.myedittext.setText("Your edit text has changed ");
                variableBinding.textview.setText("My text view has changed: " + model.editString.getValue());
            }
        }
        );

        variableBinding.checkBox.setOnCheckedChangeListener((button, isChecked) -> {
            model.isOn.postValue(true);
        });
        variableBinding.switch1.setOnCheckedChangeListener((button, isChecked) -> {
            model.isOn.postValue(true);
        });
        variableBinding.radioButton.setOnCheckedChangeListener((button, isChecked) -> {
            model.isOn.postValue(true);
        });

        model = new ViewModelProvider(this).get(MainViewModel.class);

        model.isOn.observe(this, selected -> {
           variableBinding.checkBox.setChecked(selected);
           variableBinding.radioButton.setChecked(selected);
           variableBinding.switch1.setChecked(selected);
           Toast.makeText(MainActivity.this, "The value is now: ", Toast.LENGTH_SHORT).show();
        });

        variableBinding.myimagebutton.setOnClickListener((button) -> {
            int width = variableBinding.myimagebutton.getWidth();
            int height = variableBinding.myimagebutton.getHeight();
            Toast.makeText(MainActivity.this, "The width = " + width + " and height = " + height, Toast.LENGTH_LONG).show();
        });

    }
}