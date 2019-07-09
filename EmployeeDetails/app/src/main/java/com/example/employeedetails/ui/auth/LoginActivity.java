package com.example.employeedetails.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.employeedetails.ui.common.BaseActivity;
import com.example.employeedetails.R;
import com.example.employeedetails.ui.main.DashboardActivity;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.annotation.Nullable;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;


public class LoginActivity extends BaseActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");
        setToolBar();

        final TextInputEditText userNameEt = findViewById(R.id.username);
        final TextInputEditText passwordEt = findViewById(R.id.password);

        authViewModel = ViewModelProviders.of(this).get(AuthViewModel.class);

        Button button = findViewById(R.id.login_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String userName = userNameEt.getText().toString();
                final String password = passwordEt.getText().toString();




//
//                RequestBody requestBody = new RequestBody() {
//                    @Nullable
//                    @Override
//                    public MediaType contentType() {
//                        return null;
//                    }
//
//                    @Override
//                    public void writeTo(BufferedSink sink) throws IOException {
//                        try {
//                            requestBody = object.get("username").toString() + object.get("password").toString();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                };

                authViewModel.login(userName,password);

            }
        });
        authViewModel.loginResponse.observe(LoginActivity.this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean == null) {
                    return;
                }
                if (aBoolean) {
                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    Toast.makeText(getApplicationContext(), R.string.login_successful, Toast.LENGTH_SHORT).show();
//                    intent.putExtra("response", userName);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), R.string.login_failed, Toast.LENGTH_SHORT).show();

                }
            }
        });

        Button registerButton = findViewById(R.id.material_text_button);
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });
    }
}
