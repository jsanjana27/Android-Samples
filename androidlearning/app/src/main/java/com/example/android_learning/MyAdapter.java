package com.example.android_learning;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<DatabaseModel> dbList = new ArrayList<DatabaseModel>();
    private Context context;

    public MyAdapter(Context context) {
        this.context = context;

    }

    public void setItems(List<DatabaseModel> dbList) {
        this.dbList.clear();

        if(dbList != null && dbList.size() > 0 ) {
            this.dbList.addAll(dbList);

        }
        notifyDataSetChanged();
    }


    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.user_list_row, null);


        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {
        holder.name.setText(dbList.get(position).getName());
        holder.email.setText(dbList.get(position).getEmail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FourthActivity.class);

                Bundle bundle = new Bundle();

                bundle.putLong("id", dbList.get(position).getId());


                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dbList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name, email, id;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            name = (TextView) itemLayoutView
                    .findViewById(R.id.name);

            email = (TextView) itemLayoutView.findViewById(R.id.email);
        }
    }
}
