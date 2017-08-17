package com.bxh.algorithms.OtherAlgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by buxiaohui on 17-8-10.
 */

public class FindSameElements {
    public static void find(int[] a, int[] b) {
        List<Integer> r = new ArrayList<>(Math.max(a.length, b.length));
        int i = 0;
        int j = 0;
        while (i < a.length && j < b.length) {
            if (a[i] == b[j]) {
                //a[i] != a[i - 1]  避免重复添加
                if (i == 0 || a[i] != a[i - 1]) {
                    r.add(a[i]);
                }
                i++;
                j++;
            } else if (a[i] < b[j]) {
                i++;
            } else {
                j++;
            }
        }
        System.out.println("FindSameElements->" + Arrays.asList(r));
    }

    public static void find() {
        int[] a = {1, 1, 8, 3, 7, 3, 9};
        int[] b = {7, 4, 3, 11, 88, 45, 9};
        int[] c = {1, 2, 3, 4, 5, 5, 5, 6, 7, 7};
        int[] d = {3, 4, 5, 6, 7, 8, 8, 9};
        find(a, b);
        find(c, d);
    }
}
