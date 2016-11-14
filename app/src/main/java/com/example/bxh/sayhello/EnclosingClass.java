package com.example.bxh.sayhello;

/**
 * Created by bxh on 1/1/16.
 */

public class EnclosingClass {
    public static final String TAG = "EnclosingClass";
    private String mStr = "EnclosingClass_test";

    public EnclosingClass() {
        super();
        System.out.println("classTest --NestedClass_construct");
    }

    public static final class NestedClass04 {
        public static final String TAG = "NestedClass01";

        public NestedClass04() {
            super();
            System.out.println("classTest --NestedClass04_construct");
        }

        private void test_01_NestedClass_04() {

            System.out.println("classTest --test_01_NestedClass04() 不能访问外部类的 非静态变量");
            System.out.println("classTest --test_01_NestedClass04() 只能访问外部类的 静态变量");
        }
    }

    private static final class NestedClass05 {
        public static final String TAG = "NestedClass01";

        public NestedClass05() {
            super();
            System.out.println("classTest --NestedClass05_construct");
        }

        private void test_01_NestedClass_05() {
            System.out.println("classTest --test_01_NestedClass05()");
        }
    }

    private class NestedClass01 {
        public static final String TAG = "NestedClass01";

        public NestedClass01() {
            super();
            System.out.println("classTest --NestedClass01_construct");
        }

        private void test_01_NestedClass_01() {
            System.out.println("classTest --test_01_NestedClass01()");
        }
    }

    private final class NestedClass02 {
        public static final String TAG = "NestedClass01";

        public NestedClass02() {
            super();
            System.out.println("classTest --NestedClass02_construct");
        }

        private void test_01_NestedClass_03() {
            System.out.println("classTest --test_01_NestedClass03()");
        }
    }

    public final class NestedClass03 {
        public static final String TAG = "NestedClass01";

        public NestedClass03() {
            super();
            System.out.println("classTest --NestedClass03_construct");
        }

        private void test_01_NestedClass_03() {
            System.out.println("classTest --test_01_NestedClass03()");
        }
    }
}
