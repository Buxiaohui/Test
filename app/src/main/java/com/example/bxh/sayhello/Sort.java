package com.example.bxh.sayhello;

import android.util.Log;

import com.google.gson.Gson;

/**
 * Created by bxh on 1/7/17.
 */

public class Sort {
    final static int[] array = {5, 3, 7, 11, 2, 6, 4, 8};
    final static int[] array1 = {5, 3, 2, 11};

    private static final String TAG = "sort";


    /*直接插入排序*/
    private static void directInsertSort2(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) {
                int temp = a[i];
                int j;
                for (j = i - 1; j >= 0 && a[j] > temp; j--) {
                    a[j + 1] = a[j];//把比temp大或相等的元素全部往后移动一个位置
                }
                a[j + 1] = temp;//把待排序的元素temp插入腾出位置的(j+1)
            }
            Log.d(TAG, "directInsertSort2 result=" + new Gson().toJson(a));
        }
        Log.d(TAG, "=============");
    }

    /*直接插入排序*/
    private static void directInsertSort3(int[] a) {
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
            Log.d(TAG, "directInsertSort3 result=" + new Gson().toJson(a));
        }
        Log.d(TAG, "=============");
    }

    /*折半插入排序*/
    private static void halfInsertSort1(int[] a) {
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
            Log.d(TAG, "halfInsertSort1 result=" + new Gson().toJson(a));
        }
        Log.d(TAG, "=============");
    }

    /*选择排序*/
    private static void choseInsertSort1(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                int temp = a[i];
                if (a[j] < temp) {
                    a[i] = a[j];
                    a[j] = temp;
                }
                Log.d(TAG, "choseInsertSort1 --result=" + new Gson().toJson(a));
            }
            Log.d(TAG, "choseInsertSort1 result=" + new Gson().toJson(a));
        }
        Log.d(TAG, "=============");
    }

    /*选择排序 改进*/
    private static void choseInsertSort2(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int index = i;
            int min = a[i];
            //这一步，找出最小的那个值 和 index
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < min) {
                    min = a[j];
                    index = j;
                }
                Log.d(TAG, "choseInsertSort2 --result=" + new Gson().toJson(a));
            }
            a[index] = a[i];
            a[i] = min;
            Log.d(TAG, "choseInsertSort2 result=" + new Gson().toJson(a));
        }
        Log.d(TAG, "=============");
    }

    /*冒泡排序 改进*/
    private static void bubbleSort1(int[] a) {
        for (int i = 0; i < a.length; i++) {
            //最后面已经是最大的了，没必要比较
            //所以只需要交换到 a.length - i - 1
            for (int j = 0; j < a.length - i - 1; j++) {
                int temp = a[j];
                if (a[j + 1] < temp) {
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
                Log.d(TAG, "bubbleSort1 --result=" + new Gson().toJson(a));
            }
            Log.d(TAG, "bubbleSort1 result=" + new Gson().toJson(a));
        }
        Log.d(TAG, "=============");
    }
    /*冒泡排序*/
    private static void bubbleSort2(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - 1; j++) {
                int temp = a[j];
                if (a[j + 1] < temp) {
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
                Log.d(TAG, "bubbleSort1 --result=" + new Gson().toJson(a));
            }
            Log.d(TAG, "bubbleSort1 result=" + new Gson().toJson(a));
        }
        Log.d(TAG, "=============");
    }
    public static void test() {
        choseInsertSort1(array.clone());
        choseInsertSort2(array.clone());
        directInsertSort2(array.clone());
        directInsertSort3(array.clone());
        halfInsertSort1(array.clone());
        bubbleSort1(array.clone());
    }
}
