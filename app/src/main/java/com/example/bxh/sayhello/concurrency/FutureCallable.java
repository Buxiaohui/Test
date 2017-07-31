package com.example.bxh.sayhello.concurrency;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by bxh on 7/23/17.
 */

public class FutureCallable {

    public static void main() {
        Callable<Integer> callable = new Callable<Integer>() {
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        };
        FutureTask<Integer> future = new FutureTask<Integer>(callable);
        new Thread(future).start();
        try {
            Thread.sleep(5000);// 可能做一些事情
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void main1() {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<Integer> future = threadPool.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        });
        try {
            Thread.sleep(5000);// 可能做一些事情
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交到CompletionService中的Future是按照完成的顺序排列的
     * */
    public static void main2() {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(threadPool);
        for (int i = 1; i < 5; i++) {
            final int taskID = i;
            cs.submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                    return taskID;
                }
            });
        }
        // 可能做一些事情
        for (int i = 1; i < 5; i++) {
            try {
                //取出的结果是按照完成的顺序
                System.out.println(cs.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 也可以先创建一个装Future类型的集合，用Executor提交的任务返回值添加到集合中，最后遍历集合取出数据，代码略
     * 但是这样是按照add的顺序取出来的！！！！！
     * /

     /*
     * 下面是CompletionService类注释。
     * */

    /**
     * /**
     * A {@link CompletionService} that uses a supplied {@link Executor}
     * to execute tasks.  This class arranges that submitted tasks are,
     * upon completion, placed on a queue accessible using {@code take}.
     * The class is lightweight enough to be suitable for transient use
     * when processing groups of tasks.
     *
     * <p>
     *
     * <b>Usage Examples.</b>
     *
     * Suppose you have a set of solvers for a certain problem, each
     * returning a value of some type {@code Result}, and would like to
     * run them concurrently, processing the results of each of them that
     * return a non-null value, in some method {@code use(Result r)}. You
     * could write this as:
     *
     * <pre> {@code
     * void solve(Executor e,
     *            Collection<Callable<Result>> solvers)
     *     throws InterruptedException, ExecutionException {
     *     CompletionService<Result> ecs
     *         = new ExecutorCompletionService<Result>(e);
     *     for (Callable<Result> s : solvers)
     *         ecs.submit(s);
     *     int n = solvers.size();
     *     for (int i = 0; i < n; ++i) {
     *         Result r = ecs.take().get();
     *         if (r != null)
     *             use(r);
     *     }
     * }}</pre>
     *
     * Suppose instead that you would like to use the first non-null result
     * of the set of tasks, ignoring any that encounter exceptions,
     * and cancelling all other tasks when the first one is ready:
     *
     * <pre> {@code
     * void solve(Executor e,
     *            Collection<Callable<Result>> solvers)
     *     throws InterruptedException {
     *     CompletionService<Result> ecs
     *         = new ExecutorCompletionService<Result>(e);
     *     int n = solvers.size();
     *     List<Future<Result>> futures
     *         = new ArrayList<Future<Result>>(n);
     *     Result result = null;
     *     try {
     *         for (Callable<Result> s : solvers)
     *             futures.add(ecs.submit(s));
     *         for (int i = 0; i < n; ++i) {
     *             try {
     *                 Result r = ecs.take().get();
     *                 if (r != null) {
     *                     result = r;
     *                     break;
     *                 }
     *             } catch (ExecutionException ignore) {}
     *         }
     *     }
     *     finally {
     *         for (Future<Result> f : futures)
     *             f.cancel(true);
     *     }
     *
     *     if (result != null)
     *         use(result);
     * }}</pre>
     * */
}



