package ru.aisdev.worksafety;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;

//import com.crashlytics.android.Crashlytics;
//
//import io.fabric.sdk.android.Fabric;

public class App extends Application {
    @Override
    public void onCreate() {
//        Fabric.with(this, new Crashlytics());
        MobileAds.initialize(this, "ca-app-pub-2429433114199537~6040637275");

        super.onCreate();
    }
}
