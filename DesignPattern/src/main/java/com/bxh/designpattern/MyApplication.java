package com.bxh.designpattern;

import android.app.Application;
import android.content.Context;

/**
 * Created by buxiaohui on 6/21/17.
 */

public class MyApplication extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
