package com.example.bxh.sayhello.OtherAlgorithms;

/**
 * Created by bxh on 5/5/17.
 */

public class StringCompress {
    private void test1(String s) {
        System.out.println("BBBBBB ---->compress");
        String finalStr = "";
        char temp = s.charAt(0);
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == temp) {
                count++;
                //System.out.println("BBBBBB equal count="+count);
                if (i == (s.length() - 1)) {
                    finalStr = finalStr + count + temp;
                }
            } else {
                //System.out.println("BBBBBB s.charAt(i)="+s.charAt(i));
                finalStr = finalStr + count + temp;
                count = 1;
                temp = s.charAt(i);
            }
        }
        System.out.println("BBBBBB finalStr=" + finalStr);
        test2(finalStr);

    }

    private void test2(String s) {
        System.out.println("BBBBBB then---->sort");
        int count = 0;
        int intCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) <= '9') {
                //nothing
            } else {
                intCount++;
            }
        }
        char[][] intA = new char[intCount][2];
        int eachCount = 0;
        int addNum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) <= '9') {
                eachCount = count * 10 + (int) s.charAt(i);
            } else {
                intA[addNum][0] = (char)eachCount;
                intA[addNum][1] = s.charAt(i);
                addNum++;
                eachCount = 0;
            }
        }

        for (int i = 0; i < intA.length; i++) {
            for (int x = 0; x < 2; x++) {
                //test log
                System.out.println("BBBBBB print array element x,y=" + i + ">and<" + x + " element is=" + intA[i][x]);
            }
        }
        System.out.println("BBBBBB real sort hahaha!!!");
        for (int i = 0; i < intA.length; i++) {
            char n1 = intA[i][0];
            char c1 = intA[i][1];
            for (int j = i; j < intA.length; j++) {
                char n2 = intA[j][0];
                char c2 = intA[j][1];
                if((int)n2 >= (int)n1){
                    intA[i][0] = n2;
                    intA[i][1] = c2;
                    intA[j][0] = n1;
                    intA[j][1] = c1;
                }
            }
        }
        String realFinalStr = "";
        for (int i = 0; i < intA.length; i++) {
            realFinalStr = realFinalStr+intA[i][0]+intA[i][1];
            System.out.println("BBBBBB print realFinalStr="+realFinalStr);
        }

    }
}
