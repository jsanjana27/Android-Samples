package com.example.employeedetails.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.employeedetails.data.model.Employee;
import com.example.employeedetails.ui.common.BaseActivity;
import com.example.employeedetails.R;
import com.example.employeedetails.ui.main.Dashboard;
import com.google.android.material.textfield.TextInputEditText;

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

                authViewModel.login(userName, password);

            }
        });
        authViewModel.loginResponse.observe(LoginActivity.this, new Observer<Employee>() {
            @Override
            public void onChanged(Employee employee) {
                if (employee == null) {
                    Toast.makeText(getApplicationContext(), R.string.login_failed, Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    Intent intent = new Intent(LoginActivity.this, Dashboard.class);
                    Toast.makeText(getApplicationContext(), R.string.login_successful, Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }
        });
    }
}
