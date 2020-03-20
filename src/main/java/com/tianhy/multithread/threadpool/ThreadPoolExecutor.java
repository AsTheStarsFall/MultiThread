package com.tianhy.multithread.threadpool;

import java.util.concurrent.*;

/**
 * @Desc: ThreadPoolExecutor
 * @Author: thy
 * @CreateTime: 2019/3/23
 **/
public class ThreadPoolExecutor extends java.util.concurrent.ThreadPoolExecutor {
    /**
     *
     * @param corePoolSize 池内线程初始值与最小值，就算是空闲状态，也会保持该数量线程。
     * @param maximumPoolSize 线程最大值，线程的增长始终不会超过该值。
     * @param keepAliveTime 当池内线程数高于corePoolSize时，经过多少时间多余的空闲线程才会被回收。回收前处于wait状态
     * @param unit 时间单位，可以使用TimeUnit的实例，如TimeUnit.MILLISECONDS　
     * @param workQueue 待入任务（Runnable）的等待场所，该参数主要影响调度策略，如公平与否，是否产生饿死(starving)
     * @param threadFactory 线程工厂类，有默认实现，如果有自定义的需要则需要自己实现ThreadFactory接口并作为参数传入。
     */
    public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
    }
}
