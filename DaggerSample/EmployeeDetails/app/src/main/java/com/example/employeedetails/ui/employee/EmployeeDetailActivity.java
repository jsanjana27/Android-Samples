package com.example.employeedetails.ui.employee;

import android.content.Intent;
import android.os.Bundle;

import com.example.employeedetails.data.model.Employee;
import com.example.employeedetails.databinding.ActivityEmployeeDetailBinding;
import com.example.employeedetails.ui.common.BaseActivity;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.view.Menu;
import android.view.MenuItem;

import com.example.employeedetails.R;

public class EmployeeDetailActivity extends BaseActivity {
    private static final int ON_DELETE = 1;
    String id;
    private GetEmployeeViewModel getEmployeeViewModel = new GetEmployeeViewModel();
    ActivityEmployeeDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employee_detail);

        setToolBarAndBackButton();

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id");


    }

    @Override
    protected void onStart() {
        super.onStart();

        getEmployeeViewModel.getEmployeeById(id).observe(this, new Observer<Employee>() {
            @Override
            public void onChanged(Employee employee) {
                if (employee != null) {
                    binding.setUser(employee);

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();

        if (menuId == R.id.edit) {
            Intent intent = new Intent(EmployeeDetailActivity.this, EmployeeUpdateActivity.class);
            Bundle bundle = new Bundle();

            bundle.putString("id", id);
            intent.putExtras(bundle);
            startActivityForResult(intent, ON_DELETE);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ON_DELETE && resultCode == 1000) {
            finish();
        }
    }
}