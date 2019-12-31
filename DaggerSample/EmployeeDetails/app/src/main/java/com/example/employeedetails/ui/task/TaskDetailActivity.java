package com.example.employeedetails.ui.task;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.employeedetails.R;
import com.example.employeedetails.data.model.Task;
import com.example.employeedetails.databinding.ActivityTaskDetailBinding;
import com.example.employeedetails.ui.common.BaseActivity;

public class TaskDetailActivity extends BaseActivity {
    private static final int ON_DELETE = 1;
    String id;
    private GetTaskViewModel getTaskViewModel = new GetTaskViewModel();
    ActivityTaskDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_task_detail);

        setToolBarAndBackButton();

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id");
    }

    @Override
    protected void onStart() {
        super.onStart();

        getTaskViewModel.getTaskById(id).observe(this, new Observer<Task>() {
            @Override
            public void onChanged(Task task) {
                if (task != null) {
                    binding.setTask(task);
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
            Intent intent = new Intent(TaskDetailActivity.this, TaskUpdateActivity.class);
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
