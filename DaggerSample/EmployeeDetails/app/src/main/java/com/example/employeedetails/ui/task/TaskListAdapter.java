package com.example.employeedetails.ui.task;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeedetails.R;
import com.example.employeedetails.data.model.Employee;
import com.example.employeedetails.data.model.Organisation;
import com.example.employeedetails.data.model.Task;
import com.example.employeedetails.ui.employee.EmployeeDetailActivity;
import com.example.employeedetails.ui.employee.EmployeeListAdapter;

import java.util.ArrayList;
import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {

    private final LayoutInflater mInflater;
    private List<Task> mtasks = new ArrayList<>();
    private Context context;

    public TaskListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.task_recyclerview_item, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        if (mtasks != null) {
            Task current = mtasks.get(position);
            holder.taskIdItemView.setText(current.getPrimaryId() + "");
            holder.taskItemView.setText(current.getName());
        } else {
            holder.taskItemView.setText("No data to show!");
        }
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("onClick", "onClick() called with: v = [" + v + "]");
                Intent intent = new Intent(context, TaskDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", mtasks.get(position).getTaskId());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    public void setData(List<Task> task) {
        mtasks.clear();
        if (task != null && task.size() > 0) {
            mtasks.addAll(task);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mtasks.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        private final TextView taskItemView;
        private final TextView taskIdItemView;
        public ImageView imgViewIcon;
        public View parent;

        private TaskViewHolder(View itemView) {
            super(itemView);
            taskItemView = itemView.findViewById(R.id.textView);
            taskIdItemView = itemView.findViewById(R.id.taskId);
//            imgViewIcon = (ImageView) itemView.findViewById(R.id.image);
            parent = itemView.findViewById(R.id.task_card);

        }
    }
}
