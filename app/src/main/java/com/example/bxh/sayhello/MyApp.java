package com.example.bxh.sayhello;

import android.app.Application;

import org.xutils.x;

/**
 * Created by bxh on 3/23/17.
 */

public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
