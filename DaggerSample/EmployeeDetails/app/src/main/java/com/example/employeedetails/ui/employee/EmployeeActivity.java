package com.example.employeedetails.ui.employee;

import android.content.Intent;
import android.os.Bundle;

import com.example.employeedetails.data.model.Employee;
import com.example.employeedetails.ui.common.BaseActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeedetails.R;

import java.util.List;

public class EmployeeActivity extends BaseActivity {

    EmployeeListAdapter adapter;
    private RecyclerView recyclerView;
    private GetEmployeeViewModel getEmployeeViewModel = new GetEmployeeViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        setToolBarAndBackButton();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeActivity.this, AddUserActivity.class);
                startActivity(intent);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new EmployeeListAdapter(EmployeeActivity.this);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(EmployeeActivity.this));

        getEmployeeViewModel = ViewModelProviders.of(EmployeeActivity.this).get(GetEmployeeViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getEmployeeViewModel.getEmployees().observe(EmployeeActivity.this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                if (adapter != null) {
                    adapter.setData(employees);
                }
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int menuId = item.getItemId();

        if (menuId == R.id.search) {
            onSearchRequested();
        }
        return super.onOptionsItemSelected(item);
    }
}
