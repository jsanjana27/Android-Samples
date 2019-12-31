package com.example.employeedetails;

import android.app.Application;

import com.example.employeedetails.data.local.RoomDatabase;
import com.example.employeedetails.util.PreferenceUtil;
import com.facebook.stetho.Stetho;

public class App extends Application {

    private static App app;

    public static App getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;

        PreferenceUtil.init(this, "TimeTrack", "San");

        RoomDatabase.getDatabase(this);

        Stetho.initializeWithDefaults(this);

    }
}
