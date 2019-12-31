package com.example.employeedetails.ui.project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeedetails.R;
import com.example.employeedetails.data.model.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ProjectViewHolder> {

    private final LayoutInflater mInflater;
    private List<Project> mProject = new ArrayList<>();
    private Context context;

    public ProjectListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public ProjectListAdapter.ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.project_recyclerview_item, parent, false);
        return new ProjectListAdapter.ProjectViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectListAdapter.ProjectViewHolder holder, int position) {
        if (mProject != null) {
            Project current = mProject.get(position);
            holder.projectIdItemView.setText(current.getPrimaryId()+ "");
            holder.projectItemView.setText(current.getName());
            holder.projectManagerItemView.setText(current.getManager());
        } else {
            holder.projectItemView.setText("No data to show!");
        }
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("onClick", "onClick() called with: v = [" + v + "]");
                Intent intent = new Intent(context, ProjectDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", mProject.get(position).getProjectId());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    public void setData(List<Project> project) {
        mProject.clear();
        if (project != null && project.size() > 0) {
            mProject.addAll(project);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mProject.size();
    }

    public static class ProjectViewHolder extends RecyclerView.ViewHolder {
        private final TextView projectItemView;
        private final TextView projectManagerItemView;
        private final TextView projectIdItemView;
        public View parent;

        private ProjectViewHolder(View itemView) {
            super(itemView);
            projectItemView = itemView.findViewById(R.id.projectName);
            projectManagerItemView = itemView.findViewById(R.id.manager);
            projectIdItemView = itemView.findViewById(R.id.projectId);
            parent = itemView.findViewById(R.id.project_card);
        }
    }
}
