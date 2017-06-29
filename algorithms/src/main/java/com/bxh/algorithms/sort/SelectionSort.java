package com.bxh.algorithms.sort;

import android.util.Log;

import java.util.Arrays;

/**
 * Created by buxiaohui on 6/29/17.
 */

public class SelectionSort {
    private static final String TAG = "SelectionSort";
    /*选择排序*/
    public static void choseInsertSort1(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                int temp = a[i];
                if (a[j] < temp) {
                    a[i] = a[j];
                    a[j] = temp;
                }
                Log.d(TAG, "choseInsertSort1 --result=" + Arrays.toString(a));
            }
            Log.d(TAG, "choseInsertSort1 result=" + Arrays.toString(a));
        }
        Log.d(TAG, "=============");
    }

    /*选择排序 改进*/
    public static void choseInsertSort2(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int index = i;
            int min = a[i];
            //这一步，找出最小的那个值 和 index
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < min) {
                    min = a[j];
                    index = j;
                }
                Log.d(TAG, "choseInsertSort2 --result=" + Arrays.toString(a));
            }
            a[index] = a[i];
            a[i] = min;
            Log.d(TAG, "choseInsertSort2 result=" + Arrays.toString(a));
        }
        Log.d(TAG, "=============");
    }
}
