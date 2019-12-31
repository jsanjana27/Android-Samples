package com.example.employeedetails.ui.project;

import android.app.DatePickerDialog;
import android.os.Bundle;

import com.example.employeedetails.data.model.Project;
import com.example.employeedetails.databinding.ActivityAddProjectBinding;
import com.example.employeedetails.ui.common.BaseActivity;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.employeedetails.R;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddProjectActivity extends BaseActivity {

    TextInputEditText startDateEt;
    TextInputEditText endDateEt;
    ActivityAddProjectBinding binding;
    private AddProjectViewModel addProjectViewModel = new AddProjectViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_project);
        binding.setViewModel(addProjectViewModel);

        setToolBarAndBackButton();
        addProjectViewModel.addResponse.observe(AddProjectActivity.this, new Observer<Project>() {
            @Override
            public void onChanged(Project project) {
                if (project == null) {
                    Toast.makeText(getApplicationContext(), R.string.add_failed, Toast.LENGTH_SHORT).show();
                    return;
                }
                finish();
            }
        });
    }
}
