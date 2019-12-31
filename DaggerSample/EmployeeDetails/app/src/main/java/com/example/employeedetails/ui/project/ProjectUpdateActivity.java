package com.example.employeedetails.ui.project;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.employeedetails.R;
import com.example.employeedetails.data.model.Organisation;
import com.example.employeedetails.data.model.Project;
import com.example.employeedetails.databinding.ActivityProjectUpdateBinding;
import com.example.employeedetails.ui.common.BaseActivity;
import com.example.employeedetails.ui.organisation.OrgUpdateActivity;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ProjectUpdateActivity extends BaseActivity {
    String projectId;
    ActivityProjectUpdateBinding binding;
    private UpdateProjectViewModel updateProjectViewModel = new UpdateProjectViewModel();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_project_update);
        binding.setViewModel(updateProjectViewModel);
        setToolBarAndBackButton();

        Bundle bundle = getIntent().getExtras();
        projectId = bundle.getString("id");

        updateProjectViewModel.getProjectById(projectId).observe(this, new Observer<Project>() {
            @Override
            public void onChanged(Project project) {
                if (project != null) {
                    updateProjectViewModel.onUpdateProject(project);
                }
            }
        });


        updateProjectViewModel.updateResponse.observe(ProjectUpdateActivity.this, employee1 -> {
            Toast.makeText(ProjectUpdateActivity.this, "Updated successfully!", Toast.LENGTH_SHORT).show();

            finish();

        });

        Button buttonDelete = findViewById(R.id.delete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProjectUpdateActivity.this);
                builder.setMessage("Do you want to delete this record?")
                        .setTitle("Delete");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        String OrgName = orgName.getText().toString();

                        Project updateProject = new Project();

                        updateProject.setProjectId(projectId);

                        updateProjectViewModel.deleteProjectById(projectId);

                        Toast.makeText(ProjectUpdateActivity.this, "Deleted successfully!", Toast.LENGTH_SHORT).show();
                        setResult(1000);
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ProjectUpdateActivity.this, "Couldn't delete", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
