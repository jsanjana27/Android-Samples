package com.example.android_learning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FifthActivity extends AppCompatActivity {
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getLong("id");


        DatabaseModel dbModel = new FeedEntryDao().getDetailsById(id);

        EditText name = (EditText) findViewById(R.id.name);

        EditText number = (EditText) findViewById(R.id.number);

        EditText email = (EditText) findViewById(R.id.email);

        EditText address = (EditText) findViewById(R.id.address);


        TextView tv1 = findViewById(R.id.name);
        TextView tv2 = (TextView) findViewById(R.id.number);
        TextView tv3 = (TextView) findViewById(R.id.email);
        TextView tv4 = (TextView) findViewById(R.id.address);

        tv1.setText(dbModel.getName());
        tv2.setText(dbModel.getEmail());
        tv3.setText(dbModel.getNumber());
        tv4.setText(dbModel.getAddress());


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
}
