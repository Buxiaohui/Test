package com.bxh.algorithms.sort;

import android.util.Log;

import com.google.gson.Gson;

import java.util.Arrays;

/**
 * Created by bxh on 1/7/17.
 */

public class SortTestClient {
    final static int[] array = {5, 3, 7, 11, 2, 6, 4, 8};
    final static int[] array1 = {5, 3, 2, 11};

    private static final String TAG = "sort";
    static int[] a = {72, 6, 57, 88, 60, 42, 83, 73, 48, 85};


    public static void test() {
        SelectionSort.choseInsertSort1(array.clone());
        SelectionSort.choseInsertSort2(array.clone());
        InsertSort.directInsertSort2(array.clone());
        InsertSort.directInsertSort3(array.clone());
        InsertSort.halfInsertSort1(array.clone());
        BubbleSort.bubbleSort1(array.clone());

    }

}
