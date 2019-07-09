package com.example.viewpagersample;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.TextView;

import static android.app.PendingIntent.getActivity;

public class ContactDetails extends AppCompatActivity {
    private static final String[] PROJECTION =
            {
                    ContactsContract.Data._ID,
                    ContactsContract.CommonDataKinds.Phone.NUMBER,
                    ContactsContract.CommonDataKinds.Phone.TYPE,
                    ContactsContract.CommonDataKinds.Phone.LABEL
            };
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getLong("id");
        getContactDetails();
    }

    @Override
    protected void onStart() {
        super.onStart();

        TextView nameTextView = (TextView) findViewById(R.id.textView);
        TextView numberTextView = (TextView) findViewById(R.id.lookUpKey);
        TextView idTextView = (TextView) findViewById(R.id.contactId);


    }

    private void getContactDetails() {
        Cursor c = getContentResolver().query(ContactsContract.Data.CONTENT_URI, PROJECTION,ContactsContract.Data.CONTACT_ID + "=?" + " AND "
                        + ContactsContract.Data.MIMETYPE + "='" + ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "'",
                new String[]{String.valueOf(id)}, null);

        if (c != null) {
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {
                c.moveToPosition(i);
                int id = c.getInt(0);
                String number = c.getString(1);
                Log.d("ID", "getContactDetails: " + id + "Number:" + number);
            }
        }

    }
}
