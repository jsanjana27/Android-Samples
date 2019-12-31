package com.example.employeedetails.ui.organisation;

import android.content.Intent;
import android.os.Bundle;

import com.example.employeedetails.data.model.Organisation;
import com.example.employeedetails.ui.common.BaseActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.example.employeedetails.R;

import java.util.List;

public class OrganisationActivity extends BaseActivity {

    OrgListAdapter adapter;
    private RecyclerView recyclerView;
    private GetOrgViewModel getOrgViewModel = new GetOrgViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisation);

        setToolBarAndBackButton();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrganisationActivity.this, AddOrgActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new OrgListAdapter(OrganisationActivity.this);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(OrganisationActivity.this));

        getOrgViewModel = ViewModelProviders.of(OrganisationActivity.this).get(GetOrgViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getOrgViewModel.getAllOrgs().observe(OrganisationActivity.this, new Observer<List<Organisation>>() {
            @Override
            public void onChanged(List<Organisation> organisations) {
                if (adapter != null) {
                    adapter.setData(organisations);
                }
            }

        });
    }
}
