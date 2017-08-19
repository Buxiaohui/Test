package com.bxh.algorithms.leetcode;

import android.text.TextUtils;

import com.bxh.algorithms.dynamic.DynamicTest;

/**
 * Created by buxiaohui on 17-8-18.
 */

public class LeetCode3 {
    static final String TAG = "LeetCode3";

    /**
     * Given a string S, find the longest palindromic substring in S.
     * You may assume that the maximum length of S is 1000,
     * and there exists one unique longest palindromic substring.
     * 求一个字符串S中的最长回文！
     */

    public static void test() {
        LeetCode3 l = new LeetCode3();
        String[] s = {"1221", "123", "12345467876"};
//        l.getPalindromicSubStr(s[2]);
//        l.getPalindromicSubStr2(s[2]);
        l.getPalindromicSubStr1(s[2]);
    }

    /**
     * 暴力穷举０！
     */
    public String getPalindromicSubStr(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        String ps = "";
        int len = str.length();
        int max = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                String subStr = str.substring(i, j + 1);
                int subLen = subStr.length();
                System.out.println(TAG + " subStr=" + subStr + "  subLen=" + subStr.length());
                if (isPalindromicSubStr(subStr)) {
                    if (max < subLen) {
                        max = subLen;
                        ps = subStr;
                    }

                }
            }
        }
        System.out.println(TAG + "  ps=" + ps + "  subLen=" + ps.length());
        return ps;
    }

    public boolean isPalindromicSubStr(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        int len = str.length();
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) != str.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }

    String expandAroundCenter(String s, int pos) {
        int l = pos, r = pos;
        int n = s.length();
        while (l >= 0 && r <= n - 1 && s.charAt(l) == s.charAt(r)) {  //向当前位置两侧移动指针
            l--;
            r++;
        }
        return s.substring(l + 1, r - 1);
    }

    /**
     * 从每个单独的字符s[i]和(s[i], s[i+1])开始展开，求两侧的最长palindrome
     * 利用指针检测
     * 时间复杂度是O(n*n)，空间复杂度O(1);
     */
    String getPalindromicSubStr2(String s) {
        int n = s.length();
        if (n == 0) return "";

        String longest = s.substring(0, 1);
        for (int i = 0; i < n; i++) {
            String p2 = expandAroundCenter(s, i);
            if (p2.length() > longest.length())
                longest = p2;
        }
        System.out.println(TAG + " longest=" + longest.length());
        return longest;
    }

    /**
     * 动态规划
     * http://blog.csdn.net/PenyPeng/article/details/73433670
     * 设字符串为s，f(i,j)表示s[i..j]的最长回文子序列。
     * <p>
     * 状态转移方程如下：
     * <p>
     * 当i<j并且s[i]=s[j]时，f(i,j)=f(i+1,j-1)+2。
     * <p>
     * 当i<j并且s[i]≠s[j]时，f(i,j)=max( f(i,j-1), f(i+1,j) )。
     * <p>
     * 注意如果i+1=j并且s[i]=s[j]时，f(i,j)=f(i+1,j-1)+2=f(j,j-1)+2=2，这就是“当i>j时f(i,j)=0”的好处。
     * <p>
     * 由于f(i,j)依赖i+1，所以循环计算的时候，第一维必须倒过来计算，从s.length()-1到0。
     * 最后，s的最长回文子序列长度为f(0, s.length()-1)。
     * 须要注意的是动态规划须要额外的O(n2)的空间。
     * <p>
     * 08-18 10:28:33.985 14088-14088/? I/System.out: 0,1,1,1,1,3,3,3,3,3,5,
     * 08-18 10:28:33.985 14088-14088/? I/System.out: 0,0,1,1,1,3,3,3,3,3,5,
     * 08-18 10:28:33.985 14088-14088/? I/System.out: 0,0,0,1,1,3,3,3,3,3,5,
     * 08-18 10:28:33.985 14088-14088/? I/System.out: 0,0,0,0,1,1,1,1,1,3,5,
     * 08-18 10:28:33.986 14088-14088/? I/System.out: 0,0,0,0,0,1,1,1,1,3,5,
     * 08-18 10:28:33.986 14088-14088/? I/System.out: 0,0,0,0,0,0,1,1,1,3,5,
     * 08-18 10:28:33.986 14088-14088/? I/System.out: 0,0,0,0,0,0,0,1,1,3,3,
     * 08-18 10:28:33.986 14088-14088/? I/System.out: 0,0,0,0,0,0,0,0,1,1,1,
     * 08-18 10:28:33.986 14088-14088/? I/System.out: 0,0,0,0,0,0,0,0,0,1,1,
     * 08-18 10:28:33.986 14088-14088/? I/System.out: 0,0,0,0,0,0,0,0,0,0,1,
     */
    public String getPalindromicSubStr1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        int len = str.length();
        int[][] f = new int[len][len];
        String ps = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            f[i][i] = 1;
            for (int j = i + 1; j < str.length(); j++)
                if (str.charAt(i) == str.charAt(j))
                    f[i][j] = f[i + 1][j - 1] + 2;
                else
                    f[i][j] = Math.max(f[i][j - 1], f[i + 1][j]);
        }
        DynamicTest.printIntArr(f, TAG);
        return ps;
    }
}
