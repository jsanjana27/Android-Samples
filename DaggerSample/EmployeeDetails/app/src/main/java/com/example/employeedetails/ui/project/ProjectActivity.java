package com.example.employeedetails.ui.project;

import android.content.Intent;
import android.os.Bundle;

import com.example.employeedetails.data.model.Project;
import com.example.employeedetails.ui.common.BaseActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.example.employeedetails.R;

import java.util.List;

public class ProjectActivity extends BaseActivity {
ProjectListAdapter adapter;
    private RecyclerView recyclerView;
    private GetProjectViewModel getProjectViewModel = new GetProjectViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        setToolBarAndBackButton();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProjectActivity.this, AddProjectActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new ProjectListAdapter(ProjectActivity.this);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(ProjectActivity.this));

        getProjectViewModel = ViewModelProviders.of(ProjectActivity.this).get(GetProjectViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getProjectViewModel.getAllProjects().observe(ProjectActivity.this, new Observer<List<Project>>() {
            @Override
            public void onChanged(List<Project> projects) {
                if (adapter != null) {
                    adapter.setData(projects);
                }
            }
        });
    }
}
