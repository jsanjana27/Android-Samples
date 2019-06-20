package com.example.android_room.ui.userupdater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android_room.R;
import com.example.android_room.data.model.DatabaseModel;
import com.example.android_room.ui.UserViewModel;
import com.example.android_room.ui.common.BaseActivity;

public class UpdateActivity extends BaseActivity {
    long id;
    private UserViewModel muserViewModel = new UserViewModel();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        setToolBarAndBackButton();

        Bundle bundle = getIntent().getExtras();
        id = bundle.getLong("id");

        DatabaseModel dbModel = muserViewModel.getDetailsById(id);

        final EditText name = (EditText) findViewById(R.id.name);
        final EditText number = (EditText) findViewById(R.id.number);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText address = (EditText) findViewById(R.id.address);


        name.setText(dbModel.getName());
        number.setText(dbModel.getEmail());
        email.setText(dbModel.getNumber());
        address.setText(dbModel.getAddress());

        Button button = findViewById(R.id.save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UserName = name.getText().toString();

                String PhoneNumber = number.getText().toString();

                String Email = email.getText().toString();

                String Address = address.getText().toString();

                DatabaseModel dbModel = new DatabaseModel();

                dbModel.setName(UserName);
                dbModel.setId(id);
                dbModel.setNumber(PhoneNumber);
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
                String UserName = name.getText().toString();

                String PhoneNumber = number.getText().toString();

                String Email = email.getText().toString();

                String Address = address.getText().toString();

                DatabaseModel dbModel = new DatabaseModel();

                dbModel.setName(UserName);
                dbModel.setId(id);
                dbModel.setNumber(PhoneNumber);
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


}
