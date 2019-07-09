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
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolBar();

        final TextInputEditText userNameEt = findViewById(R.id.username);
        final TextInputEditText passwordEt = findViewById(R.id.password);


        Button button = findViewById(R.id.login_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = userNameEt.getText().toString();
                String password = passwordEt.getText().toString();

                JSONObject object = new JSONObject();
                try {
                    object.put("username", userName);
                    object.put("password", password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json: charset=utf-8"), object.toString());

                Call<EmployeeResponse> call = ApiHandler.getService().employeeLogin(requestBody);

                call.enqueue(new Callback<EmployeeResponse>() {
                    @Override
                    public void onResponse(Call<EmployeeResponse> call, Response<EmployeeResponse> response) {
                        if (response.isSuccessful()) {
                            EmployeeResponse employeeResponse = response.body();
                            Toast.makeText(
                                    getApplicationContext(),
                                    R.string.login_successful,
                                    Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                            intent.putExtra("response", employeeResponse);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<EmployeeResponse> call, Throwable t) {
                        Toast.makeText(
                                getApplicationContext(),
                                R.string.login_failed,
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        Button registerButton = findViewById(R.id.material_text_button);
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });
    }
}
