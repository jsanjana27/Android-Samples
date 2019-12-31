package com.example.employeedetails.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.employeedetails.R;
import com.example.employeedetails.ui.auth.LoginActivity;
import com.example.employeedetails.util.PreferenceUtil;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String username = PreferenceUtil.getInstance().getEncStringValue("username", "");
        String password = PreferenceUtil.getInstance().getEncStringValue("password", "");
        String token = PreferenceUtil.getInstance().getEncStringValue("token", "");

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Intent intent = new Intent(this, LoginActivity.class);
            
            startActivity(intent);
            finish();

        } else {
            Intent intent = new Intent(this, Dashboard.class);
            startActivity(intent);
            finish();
        }

    }
}
