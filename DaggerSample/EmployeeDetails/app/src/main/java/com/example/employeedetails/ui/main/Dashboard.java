package com.example.employeedetails.ui.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.employeedetails.App;
import com.example.employeedetails.R;
import com.example.employeedetails.ui.auth.LoginActivity;
import com.example.employeedetails.ui.common.BaseActivity;
import com.example.employeedetails.ui.organisation.OrganisationActivity;
import com.example.employeedetails.ui.project.ProjectActivity;
import com.example.employeedetails.ui.task.TaskActivity;
import com.example.employeedetails.ui.employee.EmployeeActivity;
import com.example.employeedetails.util.PreferenceUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

public class Dashboard extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        setToolBar();

        String username = PreferenceUtil.getInstance().getEncStringValue("username", "");

        String firstName = PreferenceUtil.getInstance().getEncStringValue("firstName","");

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);


        TextView nameText = header.findViewById(R.id.firstName);
        TextView usernameText = header.findViewById(R.id.userName);

        nameText.setText(firstName);
        usernameText.setText(username);


        CardView cardView1 = (CardView) findViewById(R.id.organisation);
        CardView cardView2 = (CardView) findViewById(R.id.employee);
        CardView cardView3 = (CardView) findViewById(R.id.project);
        CardView cardView4 = (CardView) findViewById(R.id.task);
        cardView1.setOnClickListener(this);
        cardView2.setOnClickListener(this);
        cardView3.setOnClickListener(this);
        cardView4.setOnClickListener(this);

        FloatingActionButton fab = findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Bundle userBundle = getIntent().getExtras();

//        HashMap<String, String> header = new HashMap<>();
//        header.put("Content-Type", "application/json");
//        header.put("authorization", "Bearer ");

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView1 = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

//        } else if (id == R.id.nav_slideshow) {
//
        }
           else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if(id == R.id.logout) {
            Intent intent = new Intent(Dashboard.this, LoginActivity.class);
            SharedPreferences sharedPreferences = App.getApp().getSharedPreferences(App.getApp().getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.organisation:
                Intent intent = new Intent(Dashboard.this, OrganisationActivity.class);
                startActivity(intent);
                break;
            case R.id.employee:
                Intent intent1 = new Intent(Dashboard.this, EmployeeActivity.class);
                startActivity(intent1);
                break;
            case R.id.project:
                Intent intent2 = new Intent(Dashboard.this, ProjectActivity.class);
                startActivity(intent2);
                break;
            case R.id.task:
                Intent intent3 = new Intent(Dashboard.this, TaskActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
