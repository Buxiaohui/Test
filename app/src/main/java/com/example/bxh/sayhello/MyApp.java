package com.example.bxh.sayhello;

import android.app.Application;
import android.content.Context;

import org.xutils.x;

/**
 * Created by bxh on 3/23/17.
 */

public class MyApp extends Application{
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        //x.Ext.init(this);
    }
}
