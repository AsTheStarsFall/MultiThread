package com.tianhy.multithread.threadpool.define;

import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Desc: 自定义线程池
 * @Author: thy
 * @CreateTime: 2019/4/8
 **/
public class MyThreadPoolExecutor extends ThreadPoolExecutor {

    private ConcurrentHashMap<String, Date> startTime;

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        this.startTime = new ConcurrentHashMap<>();
    }

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }


    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        startTime.put(String.valueOf(r.hashCode()), new Date());
        super.beforeExecute(t, r);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        Date startTime1 = this.startTime.remove(String.valueOf(r.hashCode()));
        Date finishTime = new Date();
        long diff = finishTime.getTime() - startTime1.getTime();
        System.out.println("总耗时：" + diff);
        System.out.println("任务总数量：" + this.getTaskCount());
        super.afterExecute(r, t);

    }

    @Override
    public void shutdown() {
        System.out.println("已经执行的任务数量 ：" + this.getCompletedTaskCount());
        ;
        super.shutdown();
    }

    public static ExecutorService MyExecutors() {
        return new MyThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<>());
    }
}
