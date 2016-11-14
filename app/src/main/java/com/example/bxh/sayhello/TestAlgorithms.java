package com.example.bxh.sayhello;

import android.util.Log;

/**
 * Created by bxh on 11/13/16.
 */

public class TestAlgorithms {
    private final static String TAG = "TestAlgorithms";

    public void test01() {
        Log.d(TAG, "onQueryTextSubmit Integer.toBinaryString=" + Integer.toBinaryString(10));
        String s = "";
        for (int n = 10; n > 0; n /= 2) {
            s = (n % 2) + s;
            Log.d(TAG, "onQueryTextSubmit s=" + s);
        }
    }

    public static double test02(int N) {
        Log.d(TAG, "test01 N=" + N);
        if (N - 1 >= 0) {
            return Math.log(N) + test02(N - 1);
        }
        return 0;
    }

}
