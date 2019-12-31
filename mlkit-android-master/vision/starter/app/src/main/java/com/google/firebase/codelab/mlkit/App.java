package com.google.firebase.codelab.mlkit;

import android.app.Application;

import com.google.firebase.FirebaseApp;

public class App extends Application {

    private static App app;

    public static App getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        FirebaseApp.initializeApp(this);
    }
}
