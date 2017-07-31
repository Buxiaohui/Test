package com.example.bxh.sayhello.concurrency;

/**
 * Created by bxh on 7/23/17.
 */

public class LockTestClient {

    public static void test() {
        LockTest.test();
        ThreadTest0.test();
    }

}
