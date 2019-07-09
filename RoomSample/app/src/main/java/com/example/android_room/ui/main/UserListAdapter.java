package com.example.android_room.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.android_room.ui.detail.DetailActivity;
import com.example.android_room.R;
import com.example.android_room.data.model.DatabaseModel;

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
            Log.d("URL", "onBindViewHolder: " + current.getPhotoUrl());
            if (!TextUtils.isEmpty(current.getPhotoUrl())) {
                RequestOptions cropOptions = new RequestOptions().transform(new CircleCrop());

                Glide.with(holder.itemView.getContext()).load(current.getPhotoUrl())
                        .fallback(R.drawable.ic_action_name).apply(cropOptions).into(holder.imgViewIcon);
            }
        } else {
            holder.userItemView.setText("No Word");

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
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
        public ImageView imgViewIcon;

        private UserViewHolder(View itemView) {
            super(itemView);
            userItemView = itemView.findViewById(R.id.textView);
            imgViewIcon = (ImageView) itemView.findViewById(R.id.image);

        }
    }
}
