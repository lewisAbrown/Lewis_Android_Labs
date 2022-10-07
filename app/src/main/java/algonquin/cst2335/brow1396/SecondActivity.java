package algonquin.cst2335.brow1396;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;

import algonquin.cst2335.brow1396.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    ActivitySecondBinding binding;
    Bitmap mBitmap;

    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {

                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {

                        Intent data = result.getData();
                        Bitmap thumbnail = data.getParcelableExtra("data");
                        binding.profileImage.setImageBitmap(thumbnail);
                        FileOutputStream fOut = null;

                        try {
                            fOut = openFileOutput("Picture.png", Context.MODE_PRIVATE);
                            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                            fOut.flush();
                            fOut.close();
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent fromPrevious = getIntent();
        String emailAddress = fromPrevious.getStringExtra("EmailAddress");

        binding.textView.setText("Welcome back " + emailAddress);

        binding.button.setOnClickListener(clk -> {
            Intent call = new Intent(Intent.ACTION_DIAL);
            String phoneNumber = binding.editTextPhone.getText().toString();
            call.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(call);
        });

        binding.buttonimage.setOnClickListener(clk->
        cameraResult.launch(cameraIntent));

        File file = new File( getFilesDir(), "Picture.png");
        if(file.exists())
        {
            Bitmap theImage = BitmapFactory.decodeFile(getFilesDir().getAbsolutePath() + "/Picture.png");
            binding.profileImage.setImageBitmap(theImage);
        }
    }
}