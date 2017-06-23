package com.example.bxh.sayhello.dynamic;

import java.util.Arrays;

/**
 * Created by buxiaohui on 6/23/17.
 */

public class DynamicTest {
    /**
     * 案例１
     * 有n级台阶，一个人每次上一级或者两级，问有多少种走完n级台阶的方法
     */
    /**
     * 案例２
     * 给定一个矩阵m，从左上角开始每次只能向右走或者向下走，最后达到右下角的位置，路径中所有数字累加起来就是路径和，
     * 返回所有路径的最小路径和，如果给定的m如下，那么路径1,3,1,0,6,1,0就是最小路径和，返回12.
     * <p>
     * 1 3 5 9
     * <p>
     * 8 1 3 4
     * <p>
     * 5 0 6 1
     * <p>
     * 8 8 4 0
     * <p>
     * 分析：对于这个题目，假设m是m行n列的矩阵，那么我们用dp[m][n]来抽象这个问题，dp1[i][j]表示的是从原点到i,j位置的最短路径和。
     * 我们首先计算第一行和第一列，直接累加即可，那么对于其他位置，要么是从它左边的位置达到，要么是从上边的位置达到，
     * 我们取左边和上边的较小值，然后加上当前的路径值，就是达到当前点的最短路径。然后从左到右，从上到下依次计算即可。
     */
    int[] dp1 = new int[10];//案例1
    /**
     * 案例０
     * 问题描述
     * <p>
     * 假设有 1 元，3 元，5 元的硬币若干（无限），现在需要凑出 11 元，问如何组合才能使硬币的数量最少？
     * 问题分析
     * <p>
     * 乍看之下，我们简单的运用一下心算就能解出需要 2 个 5 元和 1 个 1 元的解。当然这里只是列出了这个问题比较简单的情况。当硬币的币制或者种类变化，并且需要凑出的总价值变大时，就很难靠简单的计算得出结论了。贪心算法可以在一定的程度上得出较优解，但不是每次都能得出最优解。
     * <p>
     * 这里运用动态规划的思路解决该问题。按照一般思路，我们先从最基本的情况来一步一步地推导。
     * <p>
     * 我们先假设一个函数 d(i) 来表示需要凑出 i 的总价值需要的最少硬币数量。
     * <p>
     * 当 i = 0 时，很显然我们可以知道 d(0) = 0。因为不要凑钱了嘛，当然也不需要任何硬币了。注意这是很重要的一步，其后所有的结果都从这一步延伸开来。
     * 当 i = 1 时，因为我们有 1 元的硬币，所以直接在第 1 步的基础上，加上 1 个 1 元硬币，得出 d(1) = 1。
     * 当 i = 2 时，因为我们并没有 2 元的硬币，所以只能拿 1 元的硬币来凑。在第 2 步的基础上，加上 1 个 1 元硬币，得出 d(2) = 2。
     * 当 i = 3 时，我们可以在第 3 步的基础上加上 1 个 1 元硬币，得到 3 这个结果。但其实我们有 3 元硬币，所以这一步的最优结果不是建立在第 3 步的结果上得来的，而是应该建立在第 1 步上，加上 1 个 3 元硬币，得到 d(3) = 1。
     * ...
     * <p>
     * 接着就不再举例了，我们来分析一下。可以看出，除了第 1 步这个看似基本的公理外，其他往后的结果都是建立在它之前得到的某一步的最优解上，加上 1 个硬币得到。得出：
     * <p>
     * d(i) = d(j) + 1
     * <p>
     * 这里 j < i。通俗地讲，我们需要凑出 i 元，就在凑出 j 的结果上再加上某一个硬币就行了。
     * <p>
     * 那这里我们加上的是哪个硬币呢。嗯，其实很简单，把每个硬币试一下就行了：
     * <p>
     * 假设最后加上的是 1 元硬币，那 d(i) = d(j) + 1 = d(i - 1) + 1。
     * 假设最后加上的是 3 元硬币，那 d(i) = d(j) + 1 = d(i - 3) + 1。
     * 假设最后加上的是 5 元硬币，那 d(i) = d(j) + 1 = d(i - 5) + 1。
     * <p>
     * 我们分别计算出 d(i - 1) + 1，d(i - 3) + 1，d(i - 5) + 1 的值，取其中的最小值，即为最优解，也就是 d(i)。
     * d(i) = min(d(i-vx)+1), and  i-vx >= 0
     */
    /**
     * 案例3：
     * 给定数组arr，返回arr的最长递增子序列的长度，比如arr=[2,1,5,3,6,4,8,9,7]，最长递增子序列为[1,3,4,8,9]返回其长度为5.
     * 分析：
     * 首先生成dp[n]的数组，dp[i]表示以必须arr[i]这个数结束的情况下产生的最大递增子序列的长度。对于第一个数来说，很明显dp[0]为1，
     * 当我们计算dp[i]的时候，我们去考察i位置之前的所有位置，找到i位置之前的最大的dp值，记为dp[j](0=<j<i),dp[j]代表以arr[j]结尾的最长递增序列，
     * 而dp[j]又是之前计算过的最大的那个值，我们在来判断arr[i]是否大于arr[j],如果大于dp[i]=dp[j]+1.计算完dp之后，我们找出dp中的最大值，即为这个串的最长递增序列。
     */
    private int[] d; // 储存结果
    private int[] coins = {1, 3, 5}; // 硬币种类

    private void d_func(int i, int num) {
        if (i == 0) {
            d[i] = 0;
            d_func(i + 1, num);
        } else {
            int min = 9999999; // 初始化一个很大的数值。当最后如果得出的结果是这个数时，说明凑不出来。
            for (int coin : coins) {
                if (i >= coin && d[i - coin] + 1 < min) {
                    min = d[i - coin] + 1;
                }
            }
            d[i] = min;

            if (i < num) {
                d_func(i + 1, num);
            }
        }
    }

    public void test() {
        //案例0
        int sum = 11; // 需要凑 11 元
        d = new int[sum + 1]; // 初始化数组

        d_func(0, sum); // 计算需要凑出 0 ～ sum 元需要的硬币数量
        for (int i = 0; i <= sum; i++) {
            System.out.println("凑齐 " + i + " 元需要 " + d[i] + " 个硬币");
        }
        //案例1
        d_fun1(3);
        System.out.println("dp1-->" + Arrays.toString(dp1));
        //案例2
        d_fun2();
        //案例3
        d_fun3();
    }

    int d_fun1(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        /*判断n-1的状态有没有被计算过*/
        if (dp1[n - 1] == 0) {
            dp1[n - 1] = d_fun1(n - 1);
        }
        if (dp1[n - 2] == 0) {
            dp1[n - 2] = d_fun1(n - 2);
        }
        return dp1[n - 1] + dp1[n - 2];
    }

    /**
     * 案例2
     */
    int d_fun2() {
        int[][] dp2 = new int[4][4];//保存结果
        int[][] data2 = {
                {1, 3, 5, 9},
                {8, 1, 3, 4},
                {5, 0, 6, 1},
                {8, 8, 4, 0}};//原始数据

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 0 && j == 0) {
                    dp2[i][j] = data2[0][0];
                } else if (i == 0) {
                    dp2[i][j] = dp2[i][j - 1] + data2[i][j];
                } else if (j == 0) {
                    dp2[i][j] = dp2[i - 1][j] + data2[i][j];
                } else {
                    int fromLeft = dp2[i][j - 1] + data2[i][j];
                    int fromTop = dp2[i - 1][j] + data2[i][j];
                    dp2[i][j] = fromLeft < fromTop ? fromLeft : fromTop;
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(dp2[i][j] + ",");
            }
            System.out.println("");
        }

        return 0;
    }

    /**
     * 案例３
     * */
    int d_fun3() {

        int[] sourceData = {2, 1, 5, 3, 6, 4, 8, 9, 7};
        int[] result = new int[sourceData.length];
        int max = sourceData[0];
        for (int i = 0; i < sourceData.length; i++) {
            if(i == 0){
                result[0] = 1;
                max = sourceData[0];
            }else {
                if(sourceData[i] > max){
                    max = sourceData[i];
                    result[i] = result[i-1]+1;
                }else{
                    result[i] = result[i-1];
                }
            }
        }
        System.out.println("d_fun3 -->"+Arrays.toString(result));
        return 0;
    }

}
