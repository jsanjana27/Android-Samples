package com.example.employeedetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
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

                EmployeeResponse employeeResponse = new EmployeeResponse();
                employeeResponse.setFirstName(firstName);
                employeeResponse.setLastName(lastName);
                employeeResponse.setUsername(userName);
                employeeResponse.setPassword(password);

                Call<EmployeeResponse> call = ApiHandler.getService().createEmployee(employeeResponse);
                call.enqueue(new Callback<EmployeeResponse>() {
                    @Override
                    public void onResponse(Call<EmployeeResponse> call, Response<EmployeeResponse> response) {
                        if (response.isSuccessful()) {
                            Log.d("test", "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                            EmployeeResponse employeeResponse = response.body();
                            Toast.makeText(
                                    getApplicationContext(),
                                    R.string.registration_successful,
                                    Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(RegisterActivity.this, DashboardActivity.class);
                            intent.putExtra("response", employeeResponse);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<EmployeeResponse> call, Throwable t) {
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
