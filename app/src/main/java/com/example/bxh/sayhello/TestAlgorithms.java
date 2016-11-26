package com.example.bxh.sayhello;

import android.os.SystemClock;
import android.util.Log;

/**
 * Created by bxh on 11/13/16.
 */

public class TestAlgorithms {
    private final static String TAG = "TestAlgorithms";

    public static double test02(int N) {
        Log.d(TAG, "test01 N=" + N);
        if (N - 1 >= 0) {
            return Math.log(N) + test02(N - 1);
        }
        return 0;
    }

    public static boolean isInside01(int target, int[][] array) {
        //final long startT = System.currentTimeMillis();
        final long startT = System.nanoTime();
        System.out.println("isInside01 start:" + startT);
        if (array == null) {
            return false;
        } else if (!(array.length > 0)) {
            return false;
        } else {
            int tx = 0;
            int ty = array.length - 1;
            while (tx >= 0 && tx < array[0].length && ty >= 0 && ty < array.length) {
                if (target < array[tx][ty]) {
                    ty--;
                } else if (target > array[tx][ty]) {
                    tx++;
                } else {
                    System.out.println("isInside01 total time:" + (System.nanoTime() - startT));
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isInside02(int target, int[][] array) {
        long startT = System.nanoTime();
        System.out.println("isInside02 start:" + startT);
        if (array == null) {
            return false;
        } else if (!(array.length > 0)) {
            return false;
        } else {
            for (int x = array.length - 1; x >= 0; x--) {
                for (int y = array[0].length - 1; y >= 0; y--) {
                    int temp = array[x][y];
                    if (target == temp) {
                        System.out.println("isInside02 total time:" + (System.nanoTime() - startT));

                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void test01() {
        Log.d(TAG, "onQueryTextSubmit Integer.toBinaryString=" + Integer.toBinaryString(10));
        String s = "";
        for (int n = 10; n > 0; n /= 2) {
            s = (n % 2) + s;
            Log.d(TAG, "onQueryTextSubmit s=" + s);
        }
    }

}
