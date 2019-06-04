package com.example.android_learning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);

        Bundle bundle = getIntent().getExtras();

        String name = bundle.getString("NAME");
        String number = bundle.getString("NUMBER");
        String email = bundle.getString("EMAIL");
        String address = bundle.getString("ADDRESS");

//        System.out.println(b.getStringExtra("Name"));

        TextView tv1 =  findViewById(R.id.textView);
        TextView tv2 = (TextView) findViewById(R.id.number);
        TextView tv3 = (TextView) findViewById(R.id.email);
        TextView tv4 = (TextView) findViewById(R.id.address);

        tv1.setText(name);
        tv2.setText(number);
        tv3.setText(email);
        tv4.setText(address);


    }
}
