package com.example.bxh.sayhello;

import android.content.Context;
import android.os.Looper;
import android.util.Log;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by buxiaohui on 6/13/17.
 */

public class Utils {
    public static void showMapEntry(Map map,String tag) {
        Iterator iter = map.entrySet().iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            stringBuilder.append("("+key).append(",").append(val+")").append(";");
        }
        Log.i(tag,stringBuilder.toString());
    }

    public static String getDefaultCachePath(Context context) {
        File file = context.getExternalCacheDir();
        return file != null ? file.getAbsolutePath() : null;
    }

    public static boolean isOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
