package com.example.broadcastsample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BroadcastReceiver br = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter("study");
//        filter.addAction("study");
        registerReceiver(br, filter);

        Log.d("MM", "onCreate: ");

    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = new Intent();
        intent.setAction("study");
        intent.putExtra("name", "Sanjana");

        sendBroadcast(intent);
//        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        private static final String TAG = "MyBroadcastReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {

            Log.d(TAG, "onReceive() called with: context = [" + context + "], intent = [" + intent + "]");

            if (intent.hasExtra("name")) {
                Log.d(TAG, "onReceive: name " + intent.getStringExtra("name"));
            }
        }
    }
}
