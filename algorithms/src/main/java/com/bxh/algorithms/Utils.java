package com.bxh.algorithms;

import android.util.Log;

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
}
