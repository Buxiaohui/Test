package com.bxh.algorithms.OtherAlgorithms;

/**
 * Created by bxh on 4/24/17.
 */

public class StringTest {
    /**
     * 暴力匹配字符串算法
     * 思路：
     * ①如果当前字符匹配成功（即S[i] == P[j]），则i++，j++
     * ②如果失配（即S[i]! = P[j]），令i = i - (j - 1)，j = 0 .相当于每次匹配失败时，i 回溯，j 被置为0。
     * 时间复杂度为O(mn)（m、n分别为文本串和模式串的长度）。无需扩展存储空间。
     * @param text 文本串
     * @param pattern 模式串
     * @return  pattern返回在text中的位置
     */
    public static int bruteForceSearchPatternInText(String text,String pattern){
        int sLen = text.length();
        int pLen = pattern.length();

        char[] s = text.toCharArray();
        char[] p = pattern.toCharArray();

        while(sLen < pLen){
            return -1;
        }

        int i = 0 ;
        int j = 0 ;
        while(i < sLen && j < pLen){
            if(s[i] == p[j]){
                //如果当前字符匹配成功（即S[i] == P[j]），则i++，j++
                i = i+1;
                j = j+1;
            }else{
                //如果失配（即S[i]! = P[j]），令i = i - (j - 1)，j = 0
                i = i - (j-1);
                j = 0;
            }
        }
        //匹配成功，返回模式串p在文本串s中的位置，否则返回-1
        if(j == pLen){
            return i-j;
        }else{
            return -1;
        }

    }

}
