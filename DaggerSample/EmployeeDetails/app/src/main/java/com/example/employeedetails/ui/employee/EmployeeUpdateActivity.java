package com.example.employeedetails.ui.employee;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.employeedetails.R;
import com.example.employeedetails.data.model.Employee;
import com.example.employeedetails.databinding.ActivityEmployeeUpdateBinding;
import com.example.employeedetails.ui.common.BaseActivity;

public class EmployeeUpdateActivity extends BaseActivity {
    String id;
    ActivityEmployeeUpdateBinding binding;
    private UpdateEmployeeViewModel updateEmployeeViewModel = new UpdateEmployeeViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employee_update);
        binding.setViewModel(updateEmployeeViewModel);

        setToolBarAndBackButton();

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id");

        updateEmployeeViewModel.getEmployeeById(id).observe(this, new Observer<Employee>() {
            @Override
            public void onChanged(Employee employee) {
                if (employee != null) {
                    updateEmployeeViewModel.updateEmployee(employee);

                }
            }
        });
                updateEmployeeViewModel.updateResponse.observe(EmployeeUpdateActivity.this, employee1 -> {
                    Toast.makeText(EmployeeUpdateActivity.this, "Updated successfully!", Toast.LENGTH_SHORT).show();

                    finish();
                });

        Button buttonDelete = findViewById(R.id.delete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EmployeeUpdateActivity.this);
                builder.setMessage("Do you want to delete this record?")
                        .setTitle("Delete");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Employee updateEmployee = new Employee();

                        updateEmployee.setUserId(id);

                        updateEmployeeViewModel.deleteById(id);

                        Toast.makeText(EmployeeUpdateActivity.this, "Deleted successfully!", Toast.LENGTH_SHORT).show();
                        setResult(1000);
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(EmployeeUpdateActivity.this, "Couldn't delete", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
