package com.example.bxh.sayhello.sometest;

import android.util.Log;

/**
 * Created by bxh on 6/8/17.
 */

public class ThreadlocalTest {
    private static final String TAG = "ThreadlocalTest";
    ThreadlocalTestIner mThreadlocalTestIner;

    public ThreadlocalTest() {
        this.mThreadlocalTestIner = new ThreadlocalTestIner();
    }

    public void test() {
        Log.i(TAG, "thread name is "+Thread.currentThread().getName()+" and tl  is " + mThreadlocalTestIner.getIntThreadLocal());
        Log.i(TAG, "thread name is "+Thread.currentThread().getName()+" and val is ="+ mThreadlocalTestIner.getIntThreadLocalVal());
        MyThread m = new MyThread(mThreadlocalTestIner);
        m.start();
        try {
            m.join();
        }catch (InterruptedException e){
            Log.i(TAG, "InterruptedException is "+e.getMessage());
        }
        Log.i(TAG, "thread name is "+Thread.currentThread().getName()+" and val is ="+ mThreadlocalTestIner.getIntThreadLocalVal());

    }

    class ThreadlocalTestIner {
        public ThreadLocal<Integer> getIntThreadLocal() {
            return intThreadLocal;
        }

        private ThreadLocal<Integer> intThreadLocal = new ThreadLocal<Integer>() {
            @Override
            protected Integer initialValue() {
                return super.initialValue();
            }

            @Override
            public Integer get() {
                return super.get();
            }

            @Override
            public void set(Integer value) {
                super.set(value);
            }

            @Override
            public void remove() {
                super.remove();
            }
        };

        public ThreadlocalTestIner() {
            setIntThreadLocal(10);
        }

        public ThreadlocalTestIner(ThreadLocal<Integer> intThreadLocal) {
            this.intThreadLocal = intThreadLocal;
        }

        public void setIntThreadLocal(int val) {
            this.intThreadLocal.set(val);
        }

        public Integer getIntThreadLocalVal() {
            return intThreadLocal.get();
        }
    }

    class MyThread extends Thread {
        ThreadlocalTestIner iner;
        public MyThread(ThreadlocalTestIner i) {
          this.iner = i;
        }

        @Override
        public void run() {
            Log.i(TAG, "thread name is "+Thread.currentThread().getName()+" and tl is " + iner.getIntThreadLocal());
            Log.i(TAG, "thread name is "+Thread.currentThread().getName()+" and val is ="+ mThreadlocalTestIner.getIntThreadLocalVal());
            iner.setIntThreadLocal(20);


            Log.i(TAG, "then set val ,thread name is "+Thread.currentThread().getName()+" and val is ="+ mThreadlocalTestIner.getIntThreadLocalVal());
        }
    }
}
