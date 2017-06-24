package com.example.bxh.sayhello.dynamic;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by buxiaohui on 6/23/17.
 */

public class DynamicTest {

    public static void printArray2(int[][] a, int xLen, int ylen) {
        System.out.println("-----------");
        for (int i = 0; i < xLen; i++) {
            for (int j = 0; j < ylen; j++) {
                String split = a[i][j] > 9 ? "," : " ,";
                System.out.print(" " + a[i][j] + split);
            }
            System.out.println("");
        }
    }

    public void test() {
        //案例0
        new Demo0().test();
        //案例1
        new Demo1().test();
        //案例2
        new Demo2().test();
        //案例3
        new Demo3().test();
        //案例4
        new Demo4().test();
        //案例5
        new Demo5().test();
    }

    public static class Demo0 {
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
        private int[] d; // 储存结果

        private int[] coins = {1, 3, 5}; // 硬币种类

        private void d_fun0(int i, int num) {
            if (i == 0) {
                d[i] = 0;
                d_fun0(i + 1, num);
            } else {
                int min = 9999999; // 初始化一个很大的数值。当最后如果得出的结果是这个数时，说明凑不出来。
                for (int coin : coins) {
                    if (i >= coin && d[i - coin] + 1 < min) {
                        min = d[i - coin] + 1;
                    }
                }
                d[i] = min;

                if (i < num) {
                    d_fun0(i + 1, num);
                }
            }
        }

        private void d_fun0() {
            int sum = 11;
            int[] dp = new int[sum + 1];
            for (int i = 0; i < dp.length; i++) {
                if (i > 0) {
                    int min = Integer.MAX_VALUE;
                    for (int j = 0; j < coins.length; j++) {
                        if (coins[j] <= i && min > (dp[i - coins[j]] + 1)) {
                            min = dp[i - coins[j]] + 1;
                        }
                    }
                    dp[i] = min;
                } else {
                    continue;
                }
            }
            System.out.println("--------");
            for (int i = 0; i <= sum; i++) {
                System.out.println("凑齐 " + i + " 元需要 " + dp[i] + " 个硬币");
            }
        }

        public void test() {
            //案例0
            int sum = 11; // 需要凑 11 元
            d = new int[sum + 1]; // 初始化数组

            d_fun0(0, sum); // 计算需要凑出 0 ～ sum 元需要的硬币数量
            for (int i = 0; i <= sum; i++) {
                System.out.println("凑齐 " + i + " 元需要 " + d[i] + " 个硬币");
            }

            d_fun0();

        }
    }

    public static class Demo1 {
        int[] dp1 = new int[10];//案例1

        /**
         * 案例１
         * 有n级台阶，一个人每次上一级或者两级，问有多少种走完n级台阶的方法
         */

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

        public void test() {
            d_fun1(3);
            System.out.println("dp1-->" + Arrays.toString(dp1));
        }
    }

    public static class Demo2 {
        int[] dp1 = new int[10];//案例1

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

        public void test() {
            d_fun2();
        }

    }

    public static class Demo3 {
        /**
         * 案例3：
         * 给定数组arr，返回arr的最长递增子序列的长度，比如arr=[2,1,5,3,6,4,8,9,7]，最长递增子序列为[1,3,4,8,9]返回其长度为5.
         * 分析：
         * 首先生成dp[n]的数组，dp[i]表示以必须arr[i]这个数结束的情况下产生的最大递增子序列的长度。对于第一个数来说，很明显dp[0]为1，
         * 当我们计算dp[i]的时候，我们去考察i位置之前的所有位置，找到i位置之前的最大的dp值，记为dp[j](0=<j<i),dp[j]代表以arr[j]结尾的最长递增序列，
         * 而dp[j]又是之前计算过的最大的那个值，我们在来判断arr[i]是否大于arr[j],如果大于dp[i]=dp[j]+1.计算完dp之后，我们找出dp中的最大值，即为这个串的最长递增序列。
         */
        int d_fun3() {

            int[] sourceData = {2, 1, 5, 3, 6, 4, 8, 9, 7};
            int[] sourceData1 = {1, 6, 4, 5, 6, 4, 8, 9, 7};
            int[] dp = new int[sourceData.length];
            int max = 0;
            for (int i = 0; i < sourceData.length; i++) {
                if (i == 0) {
                    dp[0] = 1;
                    max = dp[0];
                } else {
                    int maxOneIndex=0;
                    for (int j = 0; j < i; j++) {
                        if (dp[j] >= max) {
                            max = dp[j];
                            maxOneIndex = j;
                        }
                    }
                    if (sourceData[i] > sourceData[maxOneIndex]) {
                        dp[i] = max + 1;
                    } else {
                        dp[i] = max;
                    }


                }
            }
            System.out.println("d_fun3 -->" + Arrays.toString(dp));
            return 0;
        }

        public void test() {
            d_fun3();
        }
    }

    public static class Demo4 {


        /**
         * 案例4：
         * <p>
         * 背包问题，动态规划经典问题，一个背包有滴定的承重W，有N件物品，每件物品都有自己的价值，记录在数组V中，也都有自己的重量，记录在数组W中，
         * 每件物品只能选择要装入还是不装入背包，要求在不超过背包承重的前提下，选出的物品总价值最大。
         * <p>
         * 分析：假设物品编号从1到n，一件一件的考虑是否加入背包，假设dp[x][y]表示前x件物品，不超过重量y的时候的最大价值，枚举一下第x件物品的情况：
         * <p>
         * 情况1：如果选择了第x件物品，则前x-1件物品得到的重量不能超过y-w[x]。
         * <p>
         * 情况2：如果不选择第x件物品，则前x-1件物品得到的重量不超过y。
         * <p>
         * 所以dp[x][y]可能等于dp[x-1][y],也就是不取第x件物品的时候，价值和之前一样，也可能是dp[x-1][y-w[x]]+v[x],也就是拿第x件物品的时候，
         * 当然会获得第x件物品的价值。两种可能的选择中，应该选择价值较大的那个，也就是：
         * <p>
         * dp[x][y] = max{dp[x-1][y],dp[x-1][y-w[x]]+v[x]}
         * <p>
         * 因此，对于dp矩阵来说，行数是物品的数量n，列数是背包的重量w，从左到右，从上到下，依次计算出dp值即可。
         */
        public void test() {
            d_fun4a();
            d_fun4b();
        }


        void d_fun4a() {
            int maxW = 10;

            int[] v = {42, 12, 40, 25};
            int[] w = {7, 3, 4, 5};
            int[][] result = new int[v.length + 1][maxW + 1];

            System.out.println("w->" + Arrays.toString(w));
            System.out.println("v->" + Arrays.toString(v));
            System.out.println("------------");
            for (int i = 0; i < v.length + 1; i++) {
                for (int j = 0; j < maxW + 1; j++) {
                    if (i == 0 || j == 0) {
                        result[i][j] = 0;
                    } else if (w[i - 1] <= j) {
                        result[i][j] = Math.max(result[i - 1][j], result[i - 1][j - w[i - 1]] + v[i - 1]);
                    } else {
                        result[i][j] = result[i - 1][j];
                    }
                }
            }
            for (int i = 0; i < v.length + 1; i++) {
                for (int j = 0; j < maxW + 1; j++) {
                    String split = result[i][j] > 9 ? "," : " ,";
                    System.out.print(" " + result[i][j] + split);
                }
                System.out.println("");
            }
            /**
             * 06-24 14:05:33.218 20283-20283/? I/System.out: w->[7, 3, 4, 5]
             * 06-24 14:05:33.218 20283-20283/? I/System.out: v->[42, 12, 40, 25]
             * 06-24 14:05:33.218 20283-20283/? I/System.out: ------------
             * 06-24 14:05:33.218 20283-20283/? I/System.out:  0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 ,
             * 06-24 14:05:33.218 20283-20283/? I/System.out:  0 , 0 , 0 , 0 , 0 , 0 , 0 , 42, 42, 42, 42,
             * 06-24 14:05:33.218 20283-20283/? I/System.out:  0 , 0 , 0 , 12, 12, 12, 12, 42, 42, 42, 54,
             * 06-24 14:05:33.218 20283-20283/? I/System.out:  0 , 0 , 0 , 12, 40, 40, 40, 52, 52, 52, 54,
             * 06-24 14:05:33.218 20283-20283/? I/System.out:  0 , 0 , 0 , 12, 40, 40, 40, 52, 52, 65, 65,
             */
        }

        /**
         * 在hihocoder上面还讲到可以进一步优化内存使用。上面计算f[i][j]可以看出，在计算f[i][j]时只使用了f[i-1][0……j]，没有使用其他子问题，
         * 因此在存储子问题的解时，只存储f[i-1]子问题的解即可。这样可以用两个一维数组解决，一个存储子问题，一个存储正在解决的子问题。
         * <p>
         * 再进一步思考，计算f[i][j]时只使用了f[i-1][0……j]，没有使用f[i-1][j+1]这样的话，我们先计算j的循环时，让j=M……1，只使用一个一维数组即可。
         * <p>
         * for i=1……N
         * for j=M……1
         * f[j]=max(f[j],f[j-weight[i]+value[i])
         */
        void d_fun4b() {
            //没看懂
            int maxW = 10;

            int[] v = {42, 12, 40, 25};
            int[] w = {7, 3, 4, 5};
            int[] result = new int[maxW + 1];
            for (int i = 0; i < v.length + 1; i++) {
                for (int j = maxW; j >= 0; j--) {
                    if (i == 0 || j == 0) {
                        continue;
                    }
                    if (w[i - 1] <= j) {
                        result[j] = Math.max(result[j], result[j - w[i - 1]] + v[i - 1]);
                    }
                }
            }
            System.out.println("----");
            System.out.println("result--> " + Arrays.toString(result));
            /**
             * 06-24 14:45:17.335 23017-23017/? I/System.out: result--> [0, 0, 0, 12, 40, 40, 40, 52, 52, 65, 65]
             * */
        }
    }

    public static class Demo5 {
        /**
         * 案例四：
         * <p>
         * 给定两个字符串str1和str2，返回两个字符串的最长公共子序列，例如：str1="1A2C3D4B56",str2="B1D23CA45B6A","123456"和"12C4B6"都是最长公共子序列，返回哪一个都行。
         * <p>
         * 分析：本题是非常经典的动态规划问题，假设str1的长度为M，str2的长度为N，则生成M*N的二维数组dp，dp[i][j]的含义是str1[0..i]与str2[0..j]的最长公共子序列的长度。
         * <p>
         * dp值的求法如下：
         * <p>
         * dp[i][j]的值必然和dp[i-1][j],dp[i][j-1],dp[i-1][j-1]相关，结合下面的代码来看，我们实际上是从第1行和第1列开始计算的，而把第0行和第0列都初始化为0，
         * 这是为了后面的取最大值在代码实现上的方便，dp[i][j]取三者之间的最大值。
         */
        public void test() {
            String str1 = "1A2C3D4B56";
            String str2 = "B1D23CA45B6A";
            int[][] dp = new int[str1.length() + 1][str2.length() + 1];
            for (int i = 0; i < str1.length() + 1; i++) {
                for (int j = 0; j < str2.length() + 1; j++) {
                    if (i > 0 && j > 0) {
                        if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                            dp[i][j] = dp[i - 1][j - 1] + 1;
                        } else {
                            dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                        }
                    } else {
                        continue;
                    }
                }
            }
            printArray2(dp, str1.length() + 1, str2.length());

        }
    }

    public class demo6 {
        /**
         * 案例6：
         * <p>
         * 给定两个字符串str1，str2，在给定三个整数ic,dc,rc，分别代表插入，删除和替换一个字符的代价。返回将str1
         * <p>
         * 编辑成str2的代价，比如，str1="abc",str2="adc",ic=5,dc=3,rc=2,从str1到str2，将'b'换成'd'代价最小，所以返回2.
         * <p>
         * 分析：
         * <p>
         * 在构建出动态规划表的时候，关键是搞清楚每个位置上数值的来源。首先我们生成dp[M+1][N+1]的动态规划表，M代表str1的长度，N代表str2的长度，
         * 那么dp[i][j]就是str1[0..i-1]变成str2[0...j-1]的最小代价，则dp[i][j]的来源分别来自以下四种情况：
         * <p>
         * a、首先将str1[i-1]删除，变成str1[0...i-2],然后将str1[0...i-2]变成str2[0...j-1],那么dp[i-1][j]就代表
         * 从str1[0..i-2]到str2[0...j-1]的最小代价，所以：dp[i][j] = dp[i-1][j]+dc;
         * <p>
         * b、同理也可以是从str1[0...i-1]变成str2[0...j-2]，然后在插入str2[j-1],dp[i][j-1]就代表从str1[0...i-1]变成
         * str2[0...j-2]的最小大家，所以：dp[i][j] = dp[i][j-1]+ic;
         * <p>
         * c、如果str[i-1] == str2[j-1],则只需要将str1[0...i-2]变成str2[0...j-2]，此时dp[i][j] = dp[i-1][j-1];
         * <p>
         * d、如果str1[i-1]!=str2[j-1],则我们只需要将str1[i-1]替换成str2[j-1],此时dp[i][j] = dp[i-1][j-1]+rc;
         * <p>
         * 在这四种情况当中，我们选取最小的一个，即为最小代价。
         */
        public void test() {

        }

    }

}
