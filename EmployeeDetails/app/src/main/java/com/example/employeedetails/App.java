package com.example.employeedetails;

import android.app.Application;

import com.example.employeedetails.data.local.EmployeeRoomDatabase;
import com.facebook.stetho.Stetho;

public class App extends Application {

    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;

        EmployeeRoomDatabase.getDatabase(this);

        Stetho.initializeWithDefaults(this);

    }

    public static App getApp() {
        return app;
    }
}
