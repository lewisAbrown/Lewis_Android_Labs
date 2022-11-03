package algonquin.cst2335.brow1396;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author lewis
 * @version 1.0
 * This class is the main method that will verify the users input against our predetermined conditions
 */
public class MainActivity extends AppCompatActivity {

    /** This holds the text at the centre of the screen */
    private TextView tv = null;

    /** This holds the EditText at the centre of the screen */
    private EditText et = null;

    /**This holds the Button at the centre of the screen */
    private Button btn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.textView);
        EditText et = findViewById(R.id.editText);
        Button btn = findViewById(R.id.button);

        btn.setOnClickListener( clk->{
            String password = et.getText().toString();
            if (checkPasswordComplexity(password)==true) {
                tv.setText("Your password is complex enough");
            } else{
            tv.setText("You shall not pass!");
            }
        });
    }

    /**
     * Function to check password complexity
     * @param password The string object that we are checking
     * @return Returns true if complexity conditions are met
     */
    boolean checkPasswordComplexity(String password)
    {
        boolean foundUppercase = false;
        boolean foundLowercase = false;
        boolean foundDigits = false;
        boolean foundSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c))
                foundUppercase=true;
            if (Character.isLowerCase(c))
                foundLowercase= true;
            if (Character.isDigit(c))
                foundDigits = true;
            if (isSpecialCharacter(c))
                foundSpecial = true;
        }

        if (!foundUppercase) {
            // uppercase letter missing
            Toast toast1 = Toast.makeText(getApplicationContext(),"You need at least one uppercase",Toast.LENGTH_SHORT);
            toast1.show();
            return false;
        } else if (!foundLowercase) {
            // lower case letter missing
            Toast toast2 = Toast.makeText(getApplicationContext(),"You need at least one lowercase. ",Toast.LENGTH_SHORT);
            toast2.show();
            return false;
        } else if (!foundDigits) {
            // missing digits
            Toast toast3 = Toast.makeText(getApplicationContext(),"You need at least one digit. ",Toast.LENGTH_SHORT);
            toast3.show();
            return false;
        }
        else if (!foundSpecial){
            Toast toast4 = Toast.makeText(getApplicationContext(),"You need at least one special character. ",Toast.LENGTH_SHORT);
            toast4.show();
            return false;
        }
        return true;
    }

    /**
     * This function is a switch statement to ensure their is at least one of the following special characters in the input
     * @param c is the input, this will be looped through each character
     * @return a boolean true or false depending on if conditions are met
     */
    boolean isSpecialCharacter(char c) {
        switch (c)
        {
            case '#':
            case '?':
            case '*':
            case '$':
            case '%':
            case '^':
            case '!':
            case '@':
                return true;
            default:
                return false;
        }
    }
}