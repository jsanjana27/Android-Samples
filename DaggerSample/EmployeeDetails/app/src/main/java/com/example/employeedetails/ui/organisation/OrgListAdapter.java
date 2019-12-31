package com.example.employeedetails.ui.organisation;

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
import com.example.employeedetails.ui.employee.EmployeeDetailActivity;
import com.example.employeedetails.ui.employee.EmployeeListAdapter;

import java.util.ArrayList;
import java.util.List;

public class OrgListAdapter extends RecyclerView.Adapter<OrgListAdapter.OrgViewHolder> {

    private final LayoutInflater mInflater;
    private List<Organisation> morganisation = new ArrayList<>();
    private Context context;

    public OrgListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public OrgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.org_recyclerview_item, parent, false);
        return new OrgViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrgViewHolder holder, int position) {
        if (morganisation != null) {
            Organisation current = morganisation.get(position);
            holder.orgIdItemView.setText(current.getPrimaryId() + " ");
            holder.orgItemView.setText(current.getName());
        } else {
            holder.orgItemView.setText("No data to show!");
        }

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("onClick", "onClick() called with: v = [" + v + "]");
                Intent intent = new Intent(context, OrgDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", morganisation.get(position).getOrgId());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    public void setData(List<Organisation> organisation) {
        morganisation.clear();
        if (organisation != null && organisation.size() > 0) {
            morganisation.addAll(organisation);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return morganisation.size();
    }

    public static class OrgViewHolder extends RecyclerView.ViewHolder {
        private final TextView orgItemView;
        public final TextView orgIdItemView;
        public View parent;

        private OrgViewHolder(View itemView) {
            super(itemView);
            orgItemView = itemView.findViewById(R.id.textView);
            orgIdItemView = itemView.findViewById(R.id.orgId);
            parent = itemView.findViewById(R.id.org_card);

        }
    }
}
