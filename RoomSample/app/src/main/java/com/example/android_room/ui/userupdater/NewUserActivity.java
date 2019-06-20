package com.example.android_room.ui.userupdater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.android_room.R;
import com.example.android_room.data.model.DatabaseModel;
import com.example.android_room.ui.common.BaseActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewUserActivity extends BaseActivity {
    public static final String EXTRA_REPLY = "com.example.android.userlistsql.REPLY";
    static final int REQUEST_TAKE_PHOTO = 1;
    String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        setToolBarAndBackButton();

        Button button = findViewById(R.id.save);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                ImageView image = (ImageView) findViewById(R.id.image);
                EditText name = (EditText) findViewById(R.id.name);
                EditText number = (EditText) findViewById(R.id.number);
                EditText email = (EditText) findViewById(R.id.email);
                EditText address = (EditText) findViewById(R.id.address);

                String UserName = name.getText().toString();
                String PhoneNumber = number.getText().toString();
                String Email = email.getText().toString();
                String Address = address.getText().toString();

                DatabaseModel dbModel = new DatabaseModel();

                dbModel.setPhotoUrl(currentPhotoPath);
                dbModel.setName(UserName);
                dbModel.setNumber(PhoneNumber);
                dbModel.setEmail(Email);
                dbModel.setAddress(Address);

                intent.putExtra("DatabaseModel", dbModel);

                intent.putExtra(EXTRA_REPLY, true);
                setResult(RESULT_OK, intent);

                name.setText("");
                number.setText("");
                email.setText("");
                address.setText("");

                finish();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


    public void ChooseImage(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (photoFile != null) {

                Log.d("edwe", "dispatchTakePictureIntent: " + photoFile.getAbsolutePath());
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android_room.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            ImageView imageView = (ImageView) findViewById(R.id.image);
            imageView.setImageURI(Uri.parse(new File(currentPhotoPath).getAbsolutePath()));
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
}
