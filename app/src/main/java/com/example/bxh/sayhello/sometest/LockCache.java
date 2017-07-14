package com.example.bxh.sayhello.sometest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by buxiaohui on 17-7-11.
 */

public class LockCache {
    private Map<String, Object> cache = new HashMap<String, Object>();
    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    public Object getData(String key) {
        //先从缓存中去取数据,先加上读锁
        rwl.readLock().lock();
        Object obj = null;
        try {
            obj = cache.get(key);
            if (obj == null) {
                //先解除读锁，在上写锁(必须先解除读锁才能成功上写锁)
                rwl.readLock().unlock();
                rwl.writeLock().lock();
                //去数据库取数据,再判断一次是否为null，因为有可能多个线程获得写锁
                try {
                    if (obj == null) {
                        obj = new String("obj is get from db");
                    }
                } finally {
                    //先上读锁，然后再解除写锁（这样可以成功完成，在解除写锁前获得读锁，写锁被降级--这翻译的api上的）
                    rwl.readLock().lock();
                    rwl.writeLock().unlock();//解除写锁，读锁仍然持有
                }
            }
        } finally {
            rwl.readLock().unlock();
        }
        return obj;
    }
}
