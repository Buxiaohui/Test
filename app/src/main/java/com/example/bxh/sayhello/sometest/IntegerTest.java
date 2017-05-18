package com.example.bxh.sayhello.sometest;

/**
 * Created by bxh on 5/14/17.
 */

public class IntegerTest {
    static String TAG = "IntegerTest----";
    public static void testVal() {
        System.out.println(TAG);
        System.out.println(Integer.valueOf("127") == Integer.valueOf("127"));
        System.out.println(Integer.valueOf("128") == Integer.valueOf("128"));
        System.out.println(Integer.parseInt("128") == Integer.valueOf("128"));
    }

    public static void main(String [] args)
    {
        System.out.println("Hello world");
        testVal();

    }
}
