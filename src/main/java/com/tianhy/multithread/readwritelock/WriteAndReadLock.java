package com.tianhy.multithread.readwritelock;

import lombok.extern.slf4j.Slf4j;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * {@link}
 *
 * @Desc: 读写锁
 * @Author: thy
 * @CreateTime: 2019/5/14
 **/
@Slf4j
public class WriteAndReadLock {

    private static final ReentrantReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock();
    //双端队列
    private static final Deque<Integer> data = new LinkedList<>();

    private static int value;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            es.execute(new Runnable() {
                @Override
                public void run() {
                    //每个写线程向队列添加10个数字
                    for (int i = 0; i < 10; i++) {
                        //写锁lock
                        READ_WRITE_LOCK.writeLock().lock();
                        try {
                            data.addFirst(++value);
                        } finally {
                            READ_WRITE_LOCK.writeLock().unlock();
                        }
                    }
                }
            });
            es.execute(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        Integer v = null;

                        do {
                            //读锁Lock
                            READ_WRITE_LOCK.readLock().lock();
                            try {
                                v = data.pollLast();
                                if (v == null) {
                                    Thread.yield();
                                } else {
                                    log.info("{}", v);
                                }
                            } finally {
                                READ_WRITE_LOCK.readLock().unlock();
                            }
                        } while (true);
                    }
                }
            });
        }

        es.shutdown();
        es.awaitTermination(10L, TimeUnit.SECONDS);
    }
}
