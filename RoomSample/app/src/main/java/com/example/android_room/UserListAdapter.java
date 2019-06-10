package com.example.android_room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    private final LayoutInflater mInflater;
    private List<DatabaseModel> mdbModels;
    private Context context;

    UserListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }



    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, final int position) {
        if (mdbModels != null) {
            DatabaseModel current = mdbModels.get(position);
            holder.userItemView.setText(current.getName());
        } else {
            // Covers the case of data not being ready yet.
            holder.userItemView.setText("No Word");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailActivity.class);
                Bundle bundle = new Bundle();

                bundle.putLong("id", mdbModels.get(position).getId());

                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    void setData(List<DatabaseModel> dbModel) {
        mdbModels = dbModel;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mdbModels != null)
            return mdbModels.size();
        else return 0;
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        private final TextView userItemView;

        private UserViewHolder(View itemView) {
            super(itemView);
            userItemView = itemView.findViewById(R.id.textView);
        }
    }
}
