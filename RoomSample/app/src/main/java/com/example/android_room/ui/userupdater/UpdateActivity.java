package com.example.android_room.ui.userupdater;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_room.R;
import com.example.android_room.data.model.DatabaseModel;
import com.example.android_room.ui.UserViewModel;
import com.example.android_room.ui.common.BaseActivity;

public class UpdateActivity extends BaseActivity {
    private long userId;
    private UserViewModel muserViewModel = new UserViewModel();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        setToolBarAndBackButton();

        Bundle bundle = getIntent().getExtras();
        userId = bundle.getLong("id");

        DatabaseModel dbModel = muserViewModel.getDetailsById(userId);

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
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
                builder.setMessage("Do you want to save changes?")
                        .setTitle("Save");


                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String UserName = name.getText().toString();

                        String PhoneNumber = number.getText().toString();

                        String Email = email.getText().toString();

                        String Address = address.getText().toString();

                        DatabaseModel dbModel = new DatabaseModel();

                        dbModel.setName(UserName);
                        dbModel.setId(userId);
                        dbModel.setNumber(PhoneNumber);
                        dbModel.setEmail(Email);
                        dbModel.setAddress(Address);

                        muserViewModel.updateById(dbModel);
                        Toast.makeText(UpdateActivity.this, "Updated successfully!", Toast.LENGTH_SHORT).show();

                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(UpdateActivity.this, "Couldn't save", Toast.LENGTH_SHORT).show();

                    }
                });
// Create the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });

        Button buttonDelete = findViewById(R.id.delete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
                builder.setMessage("Do you want to delete this record?")
                        .setTitle("Delete");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String UserName = name.getText().toString();

                        String PhoneNumber = number.getText().toString();

                        String Email = email.getText().toString();

                        String Address = address.getText().toString();

                        DatabaseModel dbModel = new DatabaseModel();

                        dbModel.setName(UserName);
                        dbModel.setId(userId);
                        dbModel.setNumber(PhoneNumber);
                        dbModel.setEmail(Email);
                        dbModel.setAddress(Address);

                        muserViewModel.deleteById(dbModel);
                        Toast.makeText(UpdateActivity.this, "Deleted successfully!", Toast.LENGTH_SHORT).show();
                        setResult(1000);
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(UpdateActivity.this, "Couldn't delete", Toast.LENGTH_SHORT).show();

                    }
                });
// Create the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

}
