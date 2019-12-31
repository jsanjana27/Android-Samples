package com.example.employeedetails.ui.task;

import android.content.Intent;
import android.os.Bundle;

import com.example.employeedetails.data.model.Task;
import com.example.employeedetails.ui.common.BaseActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.example.employeedetails.R;

import java.util.List;

public class TaskActivity extends BaseActivity {
    TaskListAdapter adapter;
    private RecyclerView recyclerView;
    private GetTaskViewModel getTaskViewModel = new GetTaskViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        setToolBarAndBackButton();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaskActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new TaskListAdapter(TaskActivity.this);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(TaskActivity.this));

        getTaskViewModel = ViewModelProviders.of(TaskActivity.this).get(GetTaskViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getTaskViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                if (adapter != null) {
                    adapter.setData(tasks);
                }
            }
        });
    }
}
