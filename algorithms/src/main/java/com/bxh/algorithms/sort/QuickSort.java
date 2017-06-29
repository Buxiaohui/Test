package com.bxh.algorithms.sort;

import java.util.Arrays;

/**
 * Created by bxh on 1/7/17.
 */

public class QuickSort {
    private static final String TAG = "QuickSort";

    /**
     * 快速排序相关方法
     * TODO 返回调整后基准数的位置
     */
    public int AdjustArray(int a[], int left, int right) {
        int i = left, j = right;
        int baselineValue = a[left];
        System.out.println("quick_sort1 x=" + baselineValue);
        while (i < j) {
            // 从右向左找 >=baselineValue 的数,将其与 基准数 交换
            while (i < j && a[j] >= baselineValue) {
                j--;
            }
            if (i < j) {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i++;
            }

            // 从左向右找 <baselineValue 的数，将其与 基准数 交换
            while (i < j && a[i] < baselineValue) {
                i++;
            }
            if (i < j) {
                int temp = a[j];
                a[j] = a[i];
                a[i] = temp;
                j--;
            }
        }
        return i;
    }

    /**
     * 快速排序
     */
    public void quick_sort1(int[] a, int l, int r) {
        if (l < r) {
            int i = AdjustArray(a, l, r);//先成挖坑填数法调整s[]
            //i 的左侧 < a[i] ,右侧>a[i]
            //继续对左右进行快排
            quick_sort1(a, l, i - 1); // 递归调用
            quick_sort1(a, i + 1, r);
        }
        System.out.println("quick_sort1=" + Arrays.toString(a));
    }

}
