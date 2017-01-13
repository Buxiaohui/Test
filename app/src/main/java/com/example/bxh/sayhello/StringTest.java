package com.example.bxh.sayhello;

import android.util.Log;

/**
 * Created by bxh on 12/24/16.
 */

public class StringTest {

    /**
     * 求两个字符串的最长公共子序列
     */
    public static void testString() {
        String s1 = "acgbfhk";
        String s2 = "csgefkh";
        //明显最长子序列是 cgfk 或者cgfh
        //
    }


    /**
     * 给定一个字符串s，你可以从中删除一些字符，使得剩下的串是一个回文串。
     * 如何删除才能使得回文串最长呢？输出需要删除的字符个数。
     */
    public static void testString01() {
        String s = "gologle";

    }

    /**
     * 求最长回文子串
     * 子串：连续的
     * 暴力穷举
     */
    public static String get01() {
        String str = "googlepppe";
        int length = str.length();
        String finalStr = "";
        for (int start = 0; start < length; start++) {
            for (int end = start + 1; end <= length; end++) {
                String temp = str.substring(start, end);
                if (temp != null) {
                    if (temp.length() > finalStr.length() && isMirror(temp)) {
                        finalStr = temp;
                    }
                }
            }
        }
        Log.i("test", "finalStr=" + finalStr);
        return finalStr;
    }

    public static boolean isMirror(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 给定一个字符串s，你可以从中删除一些字符，使得剩下的串是一个回文串。
     * 如何删除才能使得回文串最长呢？输出需要删除的字符个数。
     */
    public static String get02() {
        String str = "abnjac";
        return "";
    }
}
