package com.example.android_learning;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d(TAG, "buttonOnClick: ");
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);

                EditText name = (EditText) findViewById(R.id.name);
                String Ename = name.getText().toString();

                EditText number = (EditText) findViewById(R.id.number);
//                String Pnumber = PhoneNumberUtils.formatNumber(number.getText().toString());
                String Pnumber = number.getText().toString();

                EditText email = (EditText) findViewById(R.id.email);
                String Email = email.getText().toString();

                EditText address = (EditText) findViewById(R.id.address);
                String Address = address.getText().toString();

                intent.putExtra("NAME", Ename);
                intent.putExtra("NUMBER", Pnumber);
                intent.putExtra("EMAIL", Email);
                intent.putExtra("ADDRESS", Address);

                FeedEntryDao feedDao = new FeedEntryDao();
                feedDao.insert(Ename, Pnumber, Email, Address);

                startActivity(intent);
            }
        });

    }
}
