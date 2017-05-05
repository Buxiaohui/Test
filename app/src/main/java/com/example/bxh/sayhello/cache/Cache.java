package com.example.bxh.sayhello.cache;

/**
 * Created by buxiaohui on 5/5/17.
 */

import android.util.Log;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 通过PriorityQueue，使得超时的Item最先被处理，利用BlockingQueue，将Item处理的操作阻塞住。
 * 避免了遍历方式的轮训，提高了性能。在很多需要回收对象的场景都能用上。
 */
public class Cache<K, V> {
    private static final Logger LOG = Logger.getLogger(Cache.class.getName());
    private static final String TAG = "CacheTest";

    private ConcurrentMap<K, V> cacheObjMap = new ConcurrentHashMap<K, V>();

    private DelayQueue<DelayItem<Pair<K, V>>> q = new DelayQueue<DelayItem<Pair<K, V>>>();

    private Thread daemonThread;

    public Cache() {
        Runnable daemonTask = new Runnable() {
            public void run() {
                daemonCheck();
            }
        };

        daemonThread = new Thread(daemonTask);
        daemonThread.setDaemon(true);
        daemonThread.setName("Cache Daemon");
        daemonThread.start();
    }

    // 测试入口函数
    public static void main() {
        try {
            Cache<Integer, String> cache = new Cache<Integer, String>();
            cache.put(1, "aaaa", 3, TimeUnit.SECONDS);
            cache.put(2, "bbbb", 3, TimeUnit.SECONDS);
            Thread.sleep(1000 * 2);
            {
                String str = cache.get(1);
                System.out.println(str);
                String str2 = cache.get(2);
                System.out.println(str2);
            }

            Thread.sleep(1000 * 2);
            {
                String str = cache.get(1);
                String str2 = cache.get(2);
                System.out.println(str);
                System.out.println(str2);
            }
        } catch (Exception e) {

        }

    }

    private void daemonCheck() {

        if (LOG.isLoggable(Level.INFO)) {
            LOG.info("cache service started.");
        }
        Log.i(TAG, "cache service started.");
        for (; ; ) {
            try {
                Log.i(TAG, "cache service ing．．．．．．");
                DelayItem<Pair<K, V>> delayItem = q.take();
                if (delayItem != null) {
                    // 超时对象处理
                    Pair<K, V> pair = delayItem.getItem();
                    cacheObjMap.remove(pair.first, pair.second); // compare and remove
                }
            } catch (InterruptedException e) {
                if (LOG.isLoggable(Level.SEVERE)) {
                    LOG.log(Level.SEVERE, e.getMessage(), e);
                }
                break;
            }
        }

        if (LOG.isLoggable(Level.INFO)) {
            LOG.info("cache service stopped.");
        }
        Log.i(TAG, "cache service stopped.");
    }

    // 添加缓存对象
    public void put(K key, V value, long time, TimeUnit unit) {
        V oldValue = cacheObjMap.put(key, value);
        if (oldValue != null) {
            q.remove(key);
        }
        long nanoTime = TimeUnit.NANOSECONDS.convert(time, unit);
        q.put(new DelayItem<Pair<K, V>>(new Pair<K, V>(key, value), nanoTime));
    }

    public V get(K key) {
        return cacheObjMap.get(key);
    }
}