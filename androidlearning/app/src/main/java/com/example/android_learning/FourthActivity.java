package com.example.android_learning;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class FourthActivity extends AppCompatActivity {

    private static final int ON_DELETE = 1;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getLong("id");

    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseModel dbModel = new FeedEntryDao().getDetailsById(id);

        TextView tv1 = findViewById(R.id.textView);
        TextView tv2 = (TextView) findViewById(R.id.number);
        TextView tv3 = (TextView) findViewById(R.id.email);
        TextView tv4 = (TextView) findViewById(R.id.address);

        if (dbModel == null) {
            tv1.setText("");
            tv2.setText("");
            tv3.setText("");
            tv4.setText("");
            return;
        }


        tv1.setText(dbModel.getName());
        tv2.setText(dbModel.getEmail());
        tv3.setText(dbModel.getNumber());
        tv4.setText(dbModel.getAddress());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_edit, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(FourthActivity.this, FifthActivity.class);
        Bundle bundle = new Bundle();


        bundle.putLong("id", id);

        intent.putExtras(bundle);
        startActivityForResult(intent, ON_DELETE);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ON_DELETE && resultCode == 1000) {
            finish();
        }
    }
}
