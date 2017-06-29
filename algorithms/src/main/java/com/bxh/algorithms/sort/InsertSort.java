package com.bxh.algorithms.sort;

import android.util.Log;

import java.util.Arrays;

/**
 * Created by buxiaohui on 6/29/17.
 */

public class InsertSort {
    private static final String TAG = "InsertSort";

    /*直接插入排序*/
    public static void directInsertSort2(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) {
                int temp = a[i];
                int j;
                for (j = i - 1; j >= 0 && a[j] > temp; j--) {
                    a[j + 1] = a[j];//把比temp大或相等的元素全部往后移动一个位置
                }
                a[j + 1] = temp;//把待排序的元素temp插入腾出位置的(j+1)
            }
            Log.d(TAG, "directInsertSort2 result=" + Arrays.toString(a));
        }
        Log.d(TAG, "=============");
    }

    /*直接插入排序*/
    public static void directInsertSort3(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) {
                int temp = a[i];
                int j = i - 1;
                while (j >= 0 && a[j] > temp) {
                    a[j + 1] = a[j];
                    j--;
                }
                a[j + 1] = temp;
            }
            Log.d(TAG, "directInsertSort3 result=" + Arrays.toString(a));
        }
        Log.d(TAG, "=============");
    }

    /*折半插入排序*/
    public static void halfInsertSort1(int[] a) {
        int i, j, l, h, mid;
        int temp;
        for (i = 1; i < a.length; i++) {
            temp = a[i];
            l = 0;
            h = i - 1;           //认为在r[1]和r[i-1]之间已经有序
            while (l <= h)          //对有序表进行折半查找
            {
                mid = (l + h) / 2;
                if (temp > a[mid]) {
                    l = mid + 1;
                } else {
                    h = mid - 1;
                }
            }
            //前面的步骤主要是找到 插入点h
            //插入点前面的不需要移动了
            Log.d(TAG, "halfInsertSort1 h=" + h);
            for (j = i - 1; j >= h + 1; j--) {
                a[j + 1] = a[j];
            }
            a[j + 1] = temp;
            Log.d(TAG, "halfInsertSort1 result=" + Arrays.toString(a));
        }
        Log.d(TAG, "=============");
    }
}
