package com.example.bxh.sayhello;

/**
 * Created by bxh on 3/26/17.
 */


public class MyThread1 extends Thread {
    public String methodName;

    public static void method(String s) {
        System.out.println(s);
        while (true) ;
    }

    public static synchronized void method3() {
        method("静态的method3方法");
    }

    public static synchronized void method4() {
        method("静态的method4方法");
    }

    public static void main(String[] args) throws Exception {
        MyThread1 myThread1 = new MyThread1();
        for (int i = 1; i <= 4; i++) {
            myThread1.methodName = "method" + String.valueOf(i);
            new Thread(myThread1).start();
            sleep(100);
        }
    }

    public synchronized void method1() {
        method("非静态的method1方法");
    }

    public synchronized void method2() {
        method("非静态的method2方法");
    }

    public void run() {
        try {
            getClass().getMethod(methodName).invoke(this);
        } catch (Exception e) {
        }
    }
}

