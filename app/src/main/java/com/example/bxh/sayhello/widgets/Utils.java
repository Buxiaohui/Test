package com.example.bxh.sayhello.widgets;

/**
 * Created by bxh on 3/7/17.
 */

public class Utils {
    public static float getMax(float[] array) {
        float Max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (Max < array[i]) {
                Max = array[i];
            }
        }
        return Max;
    }

    public static float getMin(float[] array) {
        float Max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (Max > array[i]) {
                Max = array[i];
            }
        }
        return Max;
    }
}
