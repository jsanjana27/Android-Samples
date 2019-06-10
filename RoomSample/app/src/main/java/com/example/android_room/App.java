package com.example.android_room;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UserRoomDatabase.getDatabase(this);
    }
}
