package com.example.bxh.sayhello.sometest;

/**
 * Created by bxh on 2/23/17.
 */

public class ThreadTest {
  /*

JAVA对于多线程的安全问题提供了专业的解决方式

就是同步代码块

synchronized(对象)//这个对象可以为任意对象
{
	需要被同步的代码
}

对象如同锁，持有锁的线程可以在同步中执行
没持有锁的线程即使获取CPU的执行权，也进不去


同步的前提：
1，必须要有两个或者两个以上的线程
2，必须是多个线程使用同一个锁

必须保证同步中只能有一个线程在运行

好处：解决了多线程的安全问题

弊端：多个线程需要判断锁，较为消耗资源
*/

    public void test() {
        Tick t = new Tick();

        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        Thread t3 = new Thread(t);
        Thread t4 = new Thread(t);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }

    class Tick implements Runnable {
        Object obj = new Object();//申请一个对象
        private int tick = 50;

        public void run() {

            while (true) {
                synchronized (obj) {
                    if (tick > 0) {
                        //try{Thread.sleep(40);}catch(Exception e){}
                        System.out.println("thread test--"+Thread.currentThread().getName() + " sail --" + tick--);
                    }
                }
            }
        }
    }


}

