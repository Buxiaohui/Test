package com.example.bxh.sayhello.concurrency;

/**
 * Created by bxh on 7/23/17.
 */

public class ThreadTest0 {
    /**
     * --->子线程执行10次，然后，主线程100次<----
     * 上述这个过程，循环100次
     * */
    public static void test() {
        final Business business = new Business();
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadExecute(business, "sub");
            }
        }).start();
        threadExecute(business, "main");
    }

    public static void threadExecute(Business business, String threadType) {
        for (int i = 0; i < 100; i++) {
            try {
                if ("main".equals(threadType)) {
                    business.main(i);
                } else {
                    business.sub(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    static class Business {
        private boolean bool = true;

        public synchronized void main(int loop) throws InterruptedException {
            while (bool) {
                this.wait();
            }
            for (int i = 0; i < 100; i++) {
                System.out.println("main thread seq of " + i + ", loop of " + loop);
            }
            bool = true;
            this.notify();
        }

        public synchronized void sub(int loop) throws InterruptedException {
            while (!bool) {
                this.wait();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println("sub thread seq of " + i + ", loop of " + loop);
            }
            bool = false;
            this.notify();
        }
    }
}
