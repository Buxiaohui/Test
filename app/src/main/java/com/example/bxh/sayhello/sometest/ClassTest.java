package com.example.bxh.sayhello.sometest;

/**
 * Created by buxiaohui on 6/21/17.
 */

public class ClassTest {
    public static class A {
        private int i = 1;

        public A() {
            print();
        }

        public void print() {
            System.out.println("base i=" + i);
        }
    }

    public static class B extends A {
        private int i = 1;

        public B() {
            i = 2;
        }

        public void print() {
            System.out.println("child i=" + i);
        }

    }

    public static void test(){
        B b = new B();
    }
}
