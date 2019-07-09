package com.example.employeedetails.ui.auth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

                Call<Employee> call = ApiHandler.getService().createEmployee(employee);
                call.enqueue(new Callback<Employee>() {
                    @Override
                    public void onResponse(Call<Employee> call, Response<Employee> response) {
                        if (response.isSuccessful()) {
                            Log.d("test", "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                            Employee employee = response.body();
                            Toast.makeText(
                                    getApplicationContext(),
                                    R.string.registration_successful,
                                    Toast.LENGTH_LONG).show();

                            SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor= sharedPreferences.edit();
                            editor.putString("username", employee.getUsername());
                            editor.putString("password", employee.getPassword());
                            editor.commit();

                            Intent intent = new Intent(RegisterActivity.this, DashboardActivity.class);
                            intent.putExtra("response", employee);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Employee> call, Throwable t) {
                        Toast.makeText(
                                getApplicationContext(),
                                R.string.registration_failed,
                                Toast.LENGTH_LONG).show();
                    }
                });


            }
        });
    }
}
