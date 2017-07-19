package com.bxh.algorithms.OtherAlgorithms;

import android.util.Log;

/**
 * Created by bxh on 4/24/17.
 */

public class StringTest {
    public static final String TAG = "StringTest";

    /**
     * 暴力匹配字符串算法
     * 思路：
     * ①如果当前字符匹配成功（即S[i] == P[j]），则i++，j++
     * ②如果失配（即S[i]! = P[j]），令i = i - (j - 1)，j = 0 .相当于每次匹配失败时，i 回溯，j 被置为0。
     * 时间复杂度为O(mn)（m、n分别为文本串和模式串的长度）。无需扩展存储空间。
     *
     * @param text    文本串
     * @param pattern 模式串
     * @return pattern返回在text中的位置
     */
    public static int bruteForceSearchPatternInText(String text, String pattern) {
        int sLen = text.length();
        int pLen = pattern.length();

        char[] s = text.toCharArray();
        char[] p = pattern.toCharArray();

        while (sLen < pLen) {
            return -1;
        }

        int i = 0;
        int j = 0;
        while (i < sLen && j < pLen) {
            if (s[i] == p[j]) {
                //如果当前字符匹配成功（即S[i] == P[j]），则i++，j++
                i = i + 1;
                j = j + 1;
            } else {
                //如果失配（即S[i]! = P[j]），令i = i - (j - 1)，j = 0
                i = i - (j - 1);
                j = 0;
            }
        }
        //匹配成功，返回模式串p在文本串s中的位置，否则返回-1
        if (j == pLen) {
            return i - j;
        } else {
            return -1;
        }

    }

    public static void test() {
        //假设输入为正整数，使用递归逆序输出其String
        String result = new StringTest().getChar(12345);
        Log.i(TAG, "result=" + result);
        //other
    }

    /**
     * 假设输入为正整数，使用递归逆序输出其String
     */
    public String getChar(int a) {
        if (a < 10) {
            return "" + a;
        }
        int x = a % 10;
        return x + getChar(a / 10);
    }

    /**
     * "123345566667"->"1234567"
     */
    private String delRepeatCahr() {
        String res = "123345566667";
        int index = 0;
        String result = "";
        while (index < res.length()) {
            if (index == 0 || res.charAt(index) != res.charAt(index - 1)) {
                result = result + res.charAt(index);
            }
            index++;
        }
        return result;
    }

    private String addBigInteger() {
        String s1 = "123456789";
        String s2 = "987654321";
        StringBuilder sbf = new StringBuilder();
        //处理字符串参数，防止以0为开头的字符串
        s1 = s1.replaceAll("^(0+)", "").equals("") ? "0" : s1.replaceAll("^(0+)", "");
        s2 = s2.replaceAll("^(0+)", "").equals("") ? "0" : s2.replaceAll("^(0+)", "");
        //补位
        int len1 = s1.length();
        int len2 = s2.length();
        int maxLen = len1 > len2 ? len1 + 1 : len2 + 1;//多补一位0，这样可以避免溢出问题
        String add0 = "";
        if (len1 > len2) {
            for (int i = len2; i < maxLen; i++) {
                add0 += "0";
            }
            s2 = add0 + s2;
            s1 = "0" + s1;
        } else {
            for (int i = len1; i < maxLen; i++) {
                add0 += "0";
            }
            s1 = add0 + s1;
            s2 = "0" + s2;
        }
        //根据是否进位决定插入的数字
        boolean isCarry = false;
        for (int i = maxLen - 1; i >= 0; i--) {
            int singleSum = Integer.parseInt(s1.charAt(i) + "") + Integer.parseInt(s2.charAt(i) + "");
            if (isCarry) {
                singleSum += 1;
            }
            if (singleSum >= 10) {
                isCarry = true;
            } else {
                isCarry = false;
            }
            sbf.insert(0, singleSum % 10);
        }
        //防止未溢出时返回的字符串以0开头
        String res = sbf.toString().replaceAll("^0", "");
        return res;

    }

}
