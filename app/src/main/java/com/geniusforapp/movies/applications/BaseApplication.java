package com.geniusforapp.movies.applications;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.gsonparserfactory.GsonParserFactory;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.orhanobut.logger.Logger;

/**
 * Created by anajar on 5/14/17.
 */

public class BaseApplication extends Application {
    public static final String TAG = BaseApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        // TODO: 5/16/17 init networking library
        initCommunication();
        // TODO: 5/16/17 init logger library
        logger();

    }

    private void logger() {
        Logger.init(TAG);
    }

    private void initCommunication() {
        AndroidNetworking.initialize(getApplicationContext());
        AndroidNetworking.setParserFactory(new GsonParserFactory());
        AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
    }
}
