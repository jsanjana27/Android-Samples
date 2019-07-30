package com.example.databindingsample;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.databindingsample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ObservableArrayMap<String, Object> user = new ObservableArrayMap<>();
        user.put("firstName", "Google");
        user.put("lastName", "Inc.");
        user.put("age", 17);

//        binding.setUser(user);
    }
}
