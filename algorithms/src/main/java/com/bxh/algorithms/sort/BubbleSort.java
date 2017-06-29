package com.bxh.algorithms.sort;

import android.util.Log;

import java.util.Arrays;

/**
 * Created by buxiaohui on 6/29/17.
 */

public class BubbleSort {
    private static final String TAG = "BubbleSort";

    /*冒泡排序 改进*/
    public static void bubbleSort1(int[] a) {
        for (int i = 0; i < a.length; i++) {
            //最后面已经是最大的了，没必要比较
            //所以只需要交换到 a.length - i - 1
            for (int j = 0; j < a.length - i - 1; j++) {
                int temp = a[j];
                if (a[j + 1] < temp) {
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
                Log.d(TAG, "bubbleSort1 --result=" + Arrays.toString(a));
            }
            Log.d(TAG, "bubbleSort1 result=" + Arrays.toString(a));
        }
        Log.d(TAG, "=============");
    }

    /*冒泡排序*/
    public static void bubbleSort2(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - 1; j++) {
                int temp = a[j];
                if (a[j + 1] < temp) {
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
                Log.d(TAG, "bubbleSort1 --result=" + Arrays.toString(a));

            }
            Log.d(TAG, "bubbleSort1 result=" + Arrays.toString(a));
        }
        Log.d(TAG, "=============");
    }
}
