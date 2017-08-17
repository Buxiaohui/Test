package com.bxh.algorithms.dynamic;

import com.bxh.algorithms.dynamic.DynamicTest;

/**
 * Created by buxiaohui on 17-8-15.
 */
/**
 *
 * 窃贼
 * */
public class ThiefTest {
    int[] a = {1, 3, 10, 2, 9, 7, 11};

    public static void test() {
        ThiefTest thiefTest = new ThiefTest();
        for (int i = 0; i < 7; i++) {
            int x = thiefTest.getTotal(i);
            System.out.println("ThiefTest total is " + x);
        }

    }

    /**
     * 递归
     */
    public int getTotal(int n) {
        if (n < 0) {
            return 0;
        }
        if (getTotal(n - 1) < getTotal(n - 2) + a[n]) {
            return getTotal(n - 2) + a[n];
        } else {
            return getTotal(n - 1);
        }
    }

    public int getTotal1(int n) {
        if (n < 0) {
            return 0;
        }
        int[] dp = new int[a.length + 1];
        for (int i = 0; i < a.length; i++) {
            dp[i] = Math.max(dp[n - 1], dp[n - 2] + a[n]);
        }
        return dp[n];
    }
}
