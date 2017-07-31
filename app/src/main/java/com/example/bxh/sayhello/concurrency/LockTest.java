package com.example.bxh.sayhello.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by bxh on 7/23/17.
 */

public class LockTest {

    volatile int key = 0;
    Lock l = new ReentrantLock();
    Condition c = l.newCondition();

    public static void test() {
        LockTest demo = new LockTest();
        new Thread(demo.new A()).start();
        new Thread(demo.new B()).start();
    }

    class A implements Runnable {
        @Override
        public void run() {
            int i = 10;
            while (i > 0) {
                l.lock();
                try {
                    if (key == 1) {
                        System.out.println("A is Running");
                        System.out.println("A is Running");
                        i--;
                        key = 0;
                        System.out.println("A is signal");
                        c.signal();
                    } else {
                        System.out.println("A is awaitUninterruptibly");
                        c.awaitUninterruptibly();
                    }

                } finally {
                    l.unlock();
                }
            }
        }

    }

    class B implements Runnable {
        @Override
        public void run() {
            int i = 10;
            while (i > 0) {
                l.lock();
                try {
                    if (key == 0) {
                        System.out.println("B is Running");
                        i--;
                        key = 1;
                        System.out.println("b is signal");
                        c.signal();
                    } else {
                        System.out.println("b is awaitUninterruptibly");
                        c.awaitUninterruptibly();
                    }

                } finally {
                    l.unlock();
                }
            }
        }
    }
    /**
     * -23 11:45:37.821 474-581/? I/System.out: A is awaitUninterruptibly
     07-23 11:45:37.822 474-582/? I/System.out: B is Running
     07-23 11:45:37.822 474-582/? I/System.out: b is signal
     07-23 11:45:37.822 474-582/? I/System.out: b is awaitUninterruptibly
     07-23 11:45:37.822 474-581/? I/System.out: A is Running
     07-23 11:45:37.822 474-581/? I/System.out: A is Running
     07-23 11:45:37.822 474-581/? I/System.out: A is signal
     07-23 11:45:37.822 474-581/? I/System.out: A is awaitUninterruptibly
     07-23 11:45:37.823 474-582/? I/System.out: B is Running
     07-23 11:45:37.823 474-582/? I/System.out: b is signal
     07-23 11:45:37.823 474-582/? I/System.out: b is awaitUninterruptibly
     07-23 11:45:37.823 474-474/? D/ActivityThreadEui: check reason flags for activity window =com.example.bxh.sayhello.MainActivity reasonFlags=10000000
     07-23 11:45:37.823 474-581/? I/System.out: A is Running
     07-23 11:45:37.823 474-581/? I/System.out: A is Running
     07-23 11:45:37.823 474-581/? I/System.out: A is signal
     07-23 11:45:37.823 474-581/? I/System.out: A is awaitUninterruptibly
     07-23 11:45:37.823 474-582/? I/System.out: B is Running
     07-23 11:45:37.823 474-582/? I/System.out: b is signal
     07-23 11:45:37.823 474-582/? I/System.out: b is awaitUninterruptibly
     07-23 11:45:37.823 474-581/? I/System.out: A is Running
     07-23 11:45:37.823 474-581/? I/System.out: A is Running
     07-23 11:45:37.823 474-581/? I/System.out: A is signal
     07-23 11:45:37.823 474-581/? I/System.out: A is awaitUninterruptibly
     07-23 11:45:37.823 474-582/? I/System.out: B is Running
     07-23 11:45:37.823 474-582/? I/System.out: b is signal
     07-23 11:45:37.823 474-582/? I/System.out: b is awaitUninterruptibly
     07-23 11:45:37.823 474-581/? I/System.out: A is Running
     07-23 11:45:37.823 474-581/? I/System.out: A is Running
     07-23 11:45:37.823 474-581/? I/System.out: A is signal
     07-23 11:45:37.823 474-581/? I/System.out: A is awaitUninterruptibly
     07-23 11:45:37.823 474-582/? I/System.out: B is Running
     07-23 11:45:37.823 474-582/? I/System.out: b is signal
     07-23 11:45:37.823 474-582/? I/System.out: b is awaitUninterruptibly
     07-23 11:45:37.823 474-581/? I/System.out: A is Running
     07-23 11:45:37.823 474-581/? I/System.out: A is Running
     07-23 11:45:37.823 474-581/? I/System.out: A is signal
     07-23 11:45:37.823 474-581/? I/System.out: A is awaitUninterruptibly
     07-23 11:45:37.823 474-582/? I/System.out: B is Running
     07-23 11:45:37.823 474-582/? I/System.out: b is signal
     07-23 11:45:37.823 474-582/? I/System.out: b is awaitUninterruptibly
     07-23 11:45:37.823 474-581/? I/System.out: A is Running
     07-23 11:45:37.823 474-581/? I/System.out: A is Running
     07-23 11:45:37.823 474-581/? I/System.out: A is signal
     07-23 11:45:37.824 474-581/? I/System.out: A is awaitUninterruptibly
     07-23 11:45:37.824 474-582/? I/System.out: B is Running
     07-23 11:45:37.824 474-582/? I/System.out: b is signal
     07-23 11:45:37.824 474-582/? I/System.out: b is awaitUninterruptibly
     07-23 11:45:37.824 474-581/? I/System.out: A is Running
     07-23 11:45:37.824 474-581/? I/System.out: A is Running
     07-23 11:45:37.824 474-581/? I/System.out: A is signal
     07-23 11:45:37.824 474-581/? I/System.out: A is awaitUninterruptibly
     07-23 11:45:37.824 474-582/? I/System.out: B is Running
     07-23 11:45:37.824 474-582/? I/System.out: b is signal
     07-23 11:45:37.824 474-582/? I/System.out: b is awaitUninterruptibly
     07-23 11:45:37.824 474-581/? I/System.out: A is Running
     07-23 11:45:37.824 474-581/? I/System.out: A is Running
     07-23 11:45:37.824 474-581/? I/System.out: A is signal
     07-23 11:45:37.824 474-581/? I/System.out: A is awaitUninterruptibly
     07-23 11:45:37.824 474-582/? I/System.out: B is Running
     07-23 11:45:37.824 474-582/? I/System.out: b is signal
     07-23 11:45:37.824 474-582/? I/System.out: b is awaitUninterruptibly
     07-23 11:45:37.824 474-581/? I/System.out: A is Running
     07-23 11:45:37.824 474-581/? I/System.out: A is Running
     07-23 11:45:37.824 474-581/? I/System.out: A is signal
     07-23 11:45:37.824 474-581/? I/System.out: A is awaitUninterruptibly
     07-23 11:45:37.824 474-582/? I/System.out: B is Running
     07-23 11:45:37.824 474-582/? I/System.out: b is signal
     07-23 11:45:37.824 474-581/? I/System.out: A is Running
     07-23 11:45:37.824 474-581/? I/System.out: A is Running
     07-23 11:45:37.824 474-581/? I/System.out: A is signal
     07-23 11:45:37.825 474-474/? D/OpenGLRenderer: Dumper init 4 threads <0xd9fd1ac0>
     07-23 11:45:37.826 474-474/? D/OpenGLRenderer: <com.example.bxh.sayhello> is running.
     07-23 11:45:37.827 474-584/? D/OpenGLRenderer: Use EGL_SWAP_BEHAVIOR_PRESERVED: true
     07-23 11:45:37.858 474-474/? W/chromium: [WARNING:jni_string.cc(28)] ConvertJavaStringToUTF8 called with null string.
     07-23 11:45:37.875 474-584/? I/OpenGLRenderer: Initialized EGL, version 1.4
     07-23 11:45:38.043 474-474/com.example.bxh.sayhello I/ViewRootImpl: updateImmersive mode: gain focus

     * */
}
