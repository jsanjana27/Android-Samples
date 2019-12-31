package com.example.employeedetails.ui.employee;

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

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.EmployeeViewHolder> {

    private final LayoutInflater mInflater;
    private List<Employee> memployees = new ArrayList<>();
    private Context context;

    public EmployeeListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NotNull
    public EmployeeViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.user_card_view, parent, false);
        return new EmployeeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, final int position) {
        if (memployees != null) {
            Employee current = memployees.get(position);
            holder.userItemView.setText(current.getFirstName() + " " + current.getLastName());
            holder.userRoleView.setText(current.getRole());
            holder.userTeamView.setText(current.getTeam());
        } else {
            holder.userItemView.setText("No data to show!");
        }

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("onClick", "onClick() called with: v = [" + v + "]");
                Intent intent = new Intent(context, EmployeeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", memployees.get(position).getUserId());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    public void setData(List<Employee> employee) {
        memployees.clear();
        if (employee != null && employee.size() > 0) {
            memployees.addAll(employee);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return memployees.size();
    }

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder {
        private final TextView userItemView;
        private final TextView userRoleView;
        private final TextView userTeamView;
        public ImageView imgViewIcon;
        public View parent;

        private EmployeeViewHolder(View itemView) {
            super(itemView);
            userItemView = itemView.findViewById(R.id.textViewName);
            userRoleView = itemView.findViewById(R.id.textViewRole);
            userTeamView = itemView.findViewById(R.id.textViewTeam);
            imgViewIcon = (ImageView) itemView.findViewById(R.id.image);
            parent = itemView.findViewById(R.id.employee_card);

        }
    }
}
