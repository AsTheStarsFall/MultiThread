package com.tianhy.multithread.threadpool;

import java.util.concurrent.*;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/4/8
 **/
public class FixedThreadPool implements Runnable{
    static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    ExecutorService service = Executors.newCachedThreadPool(new MyThreadFactory());
    ExecutorService threadPoolExecutor = new ThreadPoolExecutor(0,
            Integer.MAX_VALUE,60L,
            TimeUnit.SECONDS,new SynchronousQueue<Runnable>());

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            fixedThreadPool.execute(new FixedThreadPool());
        }
        fixedThreadPool.shutdown();
    }
}


