package com.example.android_room;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UserRoomDatabase.getDatabase(this);

        Stetho.initializeWithDefaults(this);
    }
}
