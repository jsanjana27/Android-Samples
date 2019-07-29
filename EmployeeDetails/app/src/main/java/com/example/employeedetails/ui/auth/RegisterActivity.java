package com.example.employeedetails.ui.auth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.example.employeedetails.data.common.ApiHandler;
import com.example.employeedetails.ui.common.BaseActivity;
import com.example.employeedetails.ui.main.DashboardActivity;
import com.example.employeedetails.data.response.Employee;
import com.example.employeedetails.R;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends BaseActivity {

    private AuthViewModel authViewModel = new AuthViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setToolBar();

        Button button = findViewById(R.id.register_button);
        button.setOnClickListener(new View.OnClickListener() {

            final TextInputEditText userNameEt = findViewById(R.id.username);
            final TextInputEditText passwordEt = findViewById(R.id.password);
            final TextInputEditText firstNameEt = findViewById(R.id.firstName);
            final TextInputEditText lastNameEt = findViewById(R.id.lastName);

            @Override
            public void onClick(View v) {

                String userName = userNameEt.getText().toString();
                String password = passwordEt.getText().toString();
                String firstName = firstNameEt.getText().toString();
                String lastName = lastNameEt.getText().toString();

                Employee employee = new Employee();
                employee.setFirstName(firstName);
                employee.setLastName(lastName);
                employee.setUsername(userName);
                employee.setPassword(password);

                authViewModel.register(employee);

            }
        });

        authViewModel.registerResponse.observe(RegisterActivity.this, new Observer<Employee>() {
            @Override
            public void onChanged(Employee employee) {
                if(employee == null) {
                    return;
                }
                Intent intent = new Intent(RegisterActivity.this, DashboardActivity.class);
                Toast.makeText(getApplicationContext(), R.string.registration_successful, Toast.LENGTH_SHORT).show();
//                    intent.putExtra("response", userName);
                startActivity(intent);
            }
        });
    }
}
