package com.example.employeedetails.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.employeedetails.R;
import com.example.employeedetails.ui.common.BaseActivity;

import java.util.HashMap;

public class DashboardActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        setToolBar();

        Bundle bundle = getIntent().getExtras();


        HashMap<String, String> header = new HashMap<>();
    header.put("Content-Type", "application/json");
    header.put("authorization", "Bearer " );
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();

//        if (menuId == R.id.profile) {
//            Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
//            Bundle bundle = new Bundle();
//
//        }
        return super.onOptionsItemSelected(item);
    }
}
