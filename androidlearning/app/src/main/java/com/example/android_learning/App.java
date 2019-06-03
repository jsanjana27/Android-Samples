package com.example.android_learning;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FeedReaderDbHelper.init(this);

        Stetho.initializeWithDefaults(this);
    }
}
