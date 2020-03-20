package com.tianhy.multithread.threadpool.define;

import com.tianhy.multithread.threadpool.FixedThreadPool;

import java.util.concurrent.ExecutorService;

/**
 * {@link}
 *
 * @Desc: 自定义线程
 * @Author: thy
 * @CreateTime: 2019/5/11
 **/
public class ThreadPoolDemo implements Runnable {

    static ExecutorService executorService = MyThreadPoolExecutor.MyExecutors();

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
            executorService.execute(new ThreadPoolDemo());
        }
        executorService.shutdown();
    }
}
