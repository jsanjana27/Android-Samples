package com.example.android_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class UpdateActivity extends AppCompatActivity {
    long id;
    private UserViewModel muserViewModel = new UserViewModel();
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getLong("id");

        DatabaseModel dbModel = muserViewModel.getDetailsById(id);

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

                DatabaseModel dbModel = new DatabaseModel();

                dbModel.setName(Ename);
                dbModel.setId(id);
                dbModel.setNumber(Pnumber);
                dbModel.setEmail(Email);
                dbModel.setAddress(Address);

                muserViewModel.updateById(dbModel);
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

                DatabaseModel dbModel = new DatabaseModel();

                dbModel.setName(Ename);
                dbModel.setId(id);
                dbModel.setNumber(Pnumber);
                dbModel.setEmail(Email);
                dbModel.setAddress(Address);

                muserViewModel.deleteById(dbModel);
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
