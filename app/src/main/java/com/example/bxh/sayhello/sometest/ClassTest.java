package com.example.bxh.sayhello.sometest;

/**
 * Created by buxiaohui on 6/21/17.
 */

public class ClassTest {
    public static void test() {
        B b = new B();
    }

    public static class A {
        private int i = 1;

        public A() {
            print();
        }

        public void print() {
            System.out.println("A i=" + i);
        }
    }

    public static class B extends A {
        private int i = 1;

        public B() {
            i = 2;
        }

        public void print() {
            System.out.println("B i=" + i);
        }

    }

    public static class C {
        private int i = 1;

        public C(String s) {
            print(s);
        }

        //如果不定义无参的构造方法，且D类中有参构造方法没有super，会出错
        public C() {
            print("default");
        }

        public void print(String s) {
            System.out.println("C s=" + s);
        }
    }

    public static class D extends C {
        private int i = 1;

//        public D(String s) {
//            super(s);//此时D类可以没有无参构造方法，C类可以没有无参构造方法
//            print(s);
//        }

//        public D(){
//        }

        public void print(String s) {
            System.out.println("C s=" + s);
        }
    }
}
