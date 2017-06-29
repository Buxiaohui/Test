package com.bxh.algorithms.sort;

import java.util.Arrays;

/**
 * Created by buxiaohui on 6/29/17.
 */

public class ShellSort {
    private static final String TAG = "ShellSort";
    /**
     * 希尔排序
     */
    public static void shellSortTest1(int[] arg) {
        System.out.println("shellSortTest1 begin=" + Arrays.toString(arg));
        int i = 0, j = 0, k = 0;
        int key = 0;
        for (int curStep = arg.length / 2; curStep > 0; curStep = curStep / 2) {
            System.out.println("shellSortTest1 curStep=" + curStep);
            for (i = 0; i < curStep; i++) {
                //插入排序 start
                for (k = i + curStep; k < arg.length; k += curStep) {
                    key = arg[k];
                    for (j = k - curStep; j >= i; j -= curStep) {
                        if (arg[j] > key) {
                            arg[j + curStep] = arg[j];
                            arg[j] = key;
                        } else {
                            break;
                        }
                    }
                }//插入排序 end
            }
            System.out.println("shellSortTest mid=" + Arrays.toString(arg));
        }
        System.out.println("shellSortTest end=" + Arrays.toString(arg));

    }

    /**
     * 希尔排序,我写的错的
     */
    public static void shellSortTest(int[] a) {
        System.out.println("shellSortTest begin=" + Arrays.toString(a));
        int step = a.length;
        while (step != 1) {
            step = step / 2;
            System.out.println("shellSortTest step=" + step);
            for (int i = 0; i < step; i++) {
                if (a[i] > a[step + i]) {
                    int temp = a[i];
                    a[i] = a[step + i];
                    a[step + i] = temp;
                }
            }
            System.out.println("shellSortTest mid=" + Arrays.toString(a));
        }

        SelectionSort.choseInsertSort1(a);
        System.out.println("shellSortTest after=" + Arrays.toString(a));
    }

}
