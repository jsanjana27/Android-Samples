package com.example.android_room;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewUserActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.android.userlistsql.REPLY";
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button button = findViewById(R.id.save);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                EditText name = (EditText) findViewById(R.id.name);
                EditText number = (EditText) findViewById(R.id.number);
                EditText email = (EditText) findViewById(R.id.email);
                EditText address = (EditText) findViewById(R.id.address);

                String Ename = name.getText().toString();
                String Pnumber = number.getText().toString();
                String Email = email.getText().toString();
                String Address = address.getText().toString();

                DatabaseModel dbModel = new DatabaseModel();

                dbModel.setName(Ename);
                dbModel.setNumber(Pnumber);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();
        if (menuId == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
