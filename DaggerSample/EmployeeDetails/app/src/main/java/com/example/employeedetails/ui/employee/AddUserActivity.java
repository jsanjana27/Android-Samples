package com.example.employeedetails.ui.employee;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.example.employeedetails.databinding.ActivityRegisterBinding;
import com.example.employeedetails.ui.common.BaseActivity;
import com.example.employeedetails.data.model.Employee;
import com.example.employeedetails.R;

public class AddUserActivity extends BaseActivity {

    private AddEmployeeViewModel addEmployeeViewModel = new AddEmployeeViewModel();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRegisterBinding binding;
       binding = DataBindingUtil.setContentView(this, R.layout.activity_register);

       binding.setViewModel(addEmployeeViewModel);

        setToolBarAndBackButton();

        addEmployeeViewModel.registerResponse.observe(AddUserActivity.this, new Observer<Employee>() {
            @Override
            public void onChanged(Employee employee) {
                if(employee == null) {
                    Toast.makeText(getApplicationContext(), R.string.add_failed, Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(AddUserActivity.this, EmployeeActivity.class);
                Toast.makeText(getApplicationContext(), R.string.add_employee, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}
