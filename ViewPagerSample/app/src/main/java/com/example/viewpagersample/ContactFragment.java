package com.example.viewpagersample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactFragment extends Fragment {
    private static final String[] PROJECTION =
            {
                    ContactsContract.Contacts._ID,
                    ContactsContract.Contacts.LOOKUP_KEY,
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            };

    private final static int[] TO_IDS = {
            android.R.id.text1
    };
    private static final int MY_PERMISSION_READ_CONTACTS = 3;
    ArrayList<MyContacts> contactList = new ArrayList();
    RecyclerView contactRv;
    ContactsAdapter adapter;

    public ContactFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide, container, false);

        contactRv = rootView.findViewById(R.id.contactRv);

        adapter = new ContactsAdapter();
        contactRv.setAdapter(adapter);
        contactRv.setLayoutManager(new LinearLayoutManager(getActivity()));


        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getActivity(), "Permission not granted", Toast.LENGTH_LONG)
                    .show();
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CONTACTS}, MY_PERMISSION_READ_CONTACTS);

        } else {
            getContact();
        }

    }


    private void getContact() {
        Cursor cursor = getActivity().getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, PROJECTION, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                MyContacts contacts = new MyContacts();

                int contactId = cursor.getInt(0);
                String lookUpKey = cursor.getString(1);
                String displayName = cursor.getString(2);
                Log.d("Contact Id", "getContact: " + contactId + " " + lookUpKey + " " + displayName);
                contacts.setContactId(contactId);
                contacts.setLookUpKey(lookUpKey);
                contacts.setContactName(displayName);
                contactList.add(contacts);
            }

            cursor.close();
        }
        adapter.setData(contactList);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSION_READ_CONTACTS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getContact();
                } else {
                }
                return;
            }
        }
    }
}
