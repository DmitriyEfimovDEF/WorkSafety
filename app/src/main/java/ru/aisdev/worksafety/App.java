package ru.aisdev.worksafety;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class App extends Application {
    @Override
    public void onCreate() {
        Fabric.with(this, new Crashlytics());

        super.onCreate();
    }
}
