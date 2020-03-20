package com.tianhy.multithread.readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Desc: 重入读写锁，同一时刻可以允许多个线程访问，
 *        但是在写线程访问时，所有的读线程和其他的写线程都会被阻塞
 *
 * @Author: thy
 * @CreateTime: 2018/11/20
 **/
public class ReentrantReadWriteLockDemo {

    /**
     * 适用于读多写少的场景
     */

    static Map<String, Object> cacheMap = new HashMap<>();
    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    //读锁
    static Lock read = rwl.readLock();
    //写锁
    static Lock write = rwl.writeLock();


    public static final Object get(String key) {

        System.out.println("start to read data...");
        read.lock();
        try {
            return cacheMap.get(key);
        } finally {
            read.unlock();
        }
    }

    public static final Object put(String key, Object value) {

        System.out.println("start to write data...");
        write.lock();
        try {
            return cacheMap.put(key, value);
        } finally {
            write.unlock();
        }
    }


}
