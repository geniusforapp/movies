package com.geniusforapp.movies.applications;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;

/**
 * Created by anajar on 5/14/17.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
        AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);

    }
}
