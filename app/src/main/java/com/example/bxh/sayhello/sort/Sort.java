package com.example.bxh.sayhello.sort;

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
//        choseInsertSort1(array.clone());
//        choseInsertSort2(array.clone());
//        directInsertSort2(array.clone());
//        directInsertSort3(array.clone());
//        halfInsertSort1(array.clone());
//        bubbleSort1(array.clone());
        new Sort().quick_sort1(a,0,a.length-1);
    }
    static int[] a = {72,6,57,88,60,42,83,73,48,85};

    /**
     * 快速排序相关方法
     * TODO 返回调整后基准数的位置
     * */
    int AdjustArray(int a[], int left, int right) {
        int i = left, j = right;
        int x = a[left]; //a[left]即a[i]就是第一个坑
        System.out.println("quick_sort1 x="+x);
        while (i < j) {
            // 从右向左找小于x的数来填a[i]
            while (i < j && a[j] >= x) {
                j--;
            }
            if (i < j) {
                a[i] = a[j]; //将a[j]填到a[i]中，a[j]就形成了一个新的坑
                i++;
            }

            // 从左向右找大于或等于x的数来填a[j]
            while (i < j && a[i] < x) {
                i++;
            }
            if (i < j) {
                a[j] = a[i]; //将a[i]填到a[j]中，a[i]就形成了一个新的坑
                j--;
            }
            //上述步骤 交换a[i] & a[j]
        }
        //退出时，i&j 碰面，i等于j。将x填到这个坑中。
        a[i] = x;

        return i;
    }

    /**
     * 快速排序
     *
     * */
    void quick_sort1(int[] aOri, int l, int r) {
        int[] a =aOri.clone();
        if (l < r) {
            int i = AdjustArray(a, l, r);//先成挖坑填数法调整s[]
            //i 的左侧 < a[i] ,右侧>a[i]
            //继续对左右进行快排
            quick_sort1(a, l, i - 1); // 递归调用
            quick_sort1(a, i + 1, r);
        }
        System.out.println("quick_sort1="+new Gson().toJson(a));
    }




}
