package com.example.android_learning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FifthActivity extends AppCompatActivity {
    long id;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Bundle bundle = getIntent().getExtras();
        id = bundle.getLong("id");
        Log.d("MMM", "onCreate: " + id);

        DatabaseModel dbModel = new FeedEntryDao().getDetailsById(id);

        EditText name = (EditText) findViewById(R.id.name);

        EditText number = (EditText) findViewById(R.id.number);

        EditText email = (EditText) findViewById(R.id.email);

        EditText address = (EditText) findViewById(R.id.address);

        name.setText(dbModel.getName());
        number.setText(dbModel.getEmail());
        email.setText(dbModel.getNumber());
        address.setText(dbModel.getAddress());


        Button button = findViewById(R.id.save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText) findViewById(R.id.name);
                String Ename = name.getText().toString();

                EditText number = (EditText) findViewById(R.id.number);
                String Pnumber = number.getText().toString();

                EditText email = (EditText) findViewById(R.id.email);
                String Email = email.getText().toString();

                EditText address = (EditText) findViewById(R.id.address);
                String Address = address.getText().toString();


                FeedEntryDao feedDao = new FeedEntryDao();
                feedDao.update(id, Ename, Pnumber, Email, Address);

                finish();
            }
        });

        Button buttonDelete = findViewById(R.id.delete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText) findViewById(R.id.name);
                String Ename = name.getText().toString();

                EditText number = (EditText) findViewById(R.id.number);
                String Pnumber = number.getText().toString();

                EditText email = (EditText) findViewById(R.id.email);
                String Email = email.getText().toString();

                EditText address = (EditText) findViewById(R.id.address);
                String Address = address.getText().toString();


                FeedEntryDao feedDao = new FeedEntryDao();
                feedDao.delete(id);
                setResult(1000);
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

