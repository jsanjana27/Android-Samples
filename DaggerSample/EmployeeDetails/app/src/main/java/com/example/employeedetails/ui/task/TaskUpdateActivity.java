package com.example.employeedetails.ui.task;

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
import com.example.employeedetails.data.model.Organisation;
import com.example.employeedetails.data.model.Task;
import com.example.employeedetails.databinding.ActivityTaskUpdateBinding;
import com.example.employeedetails.ui.common.BaseActivity;
import com.example.employeedetails.ui.organisation.OrgUpdateActivity;

public class TaskUpdateActivity extends BaseActivity {
    String taskId;
    ActivityTaskUpdateBinding binding;
    private UpdateTaskViewModel updateTaskViewModel = new UpdateTaskViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_task_update);
        binding.setViewModel(updateTaskViewModel);

        setToolBarAndBackButton();

        Bundle bundle = getIntent().getExtras();
        taskId = bundle.getString("id");

        updateTaskViewModel.getTaskById(taskId).observe(this, new Observer<Task>() {
            @Override
            public void onChanged(Task task) {
                if (task != null) {
                    updateTaskViewModel.onUpdateTask(task);
                }
            }
        });
        updateTaskViewModel.updateResponse.observe(TaskUpdateActivity.this, employee1 -> {
            Toast.makeText(TaskUpdateActivity.this, "Updated successfully!", Toast.LENGTH_SHORT).show();

            finish();

        });

        Button buttonDelete = findViewById(R.id.delete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TaskUpdateActivity.this);
                builder.setMessage("Do you want to delete this record?")
                        .setTitle("Delete");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Task updateTask = new Task();

                        updateTask.setTaskId(taskId);

                        updateTaskViewModel.deleteTaskById(taskId);

                        Toast.makeText(TaskUpdateActivity.this, "Deleted successfully!", Toast.LENGTH_SHORT).show();
                        setResult(1000);
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(TaskUpdateActivity.this, "Couldn't delete", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
