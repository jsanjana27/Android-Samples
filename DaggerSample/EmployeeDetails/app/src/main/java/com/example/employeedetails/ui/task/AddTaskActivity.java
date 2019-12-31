package com.example.employeedetails.ui.task;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.employeedetails.R;
import com.example.employeedetails.data.model.Task;
import com.example.employeedetails.databinding.ActivityAddTaskBinding;
import com.example.employeedetails.ui.common.BaseActivity;
import com.google.android.material.textfield.TextInputEditText;

public class AddTaskActivity extends BaseActivity {
    ActivityAddTaskBinding binding;
    private AddTaskViewModel addTaskViewModel = new AddTaskViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_task);
        binding.setViewModel(addTaskViewModel);

        setToolBarAndBackButton();

        addTaskViewModel.addResponse.observe(this, new Observer<Task>() {
            @Override
            public void onChanged(Task task) {
                if (task == null) {
                    Toast.makeText(getApplicationContext(), R.string.add_failed, Toast.LENGTH_SHORT).show();
                    return;
                }
                finish();
            }
        });
    }
}
