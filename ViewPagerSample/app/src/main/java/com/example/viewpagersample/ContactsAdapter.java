package com.example.viewpagersample;

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

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {
    private List<MyContacts> myContacts;

    ContactsAdapter() {
    }

    @Override
    public void onBindViewHolder(@NonNull final ContactsViewHolder holder, final int position) {
        if (myContacts != null) {
            MyContacts current = myContacts.get(position);
            holder.contactItemView.setText(current.getContactName());
        } else {
            holder.contactItemView.setText("No Contacts");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ContactDetails.class);
                Bundle bundle = new Bundle();
                bundle.putLong("id", myContacts.get(position).getContactId());

                intent.putExtras(bundle);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    void setData(List<MyContacts> contacts) {
        myContacts = contacts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (myContacts != null) {
            return myContacts.size();
        } else
            return 0;
    }

    @NonNull
    @Override
    public ContactsAdapter.ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_list_item, parent, false);
        return new ContactsViewHolder(itemView);
    }

    public class ContactsViewHolder extends RecyclerView.ViewHolder {
        private final TextView contactItemView;

        private ContactsViewHolder(View itemView) {
            super(itemView);
            contactItemView = itemView.findViewById(R.id.textView);

        }
    }
}
