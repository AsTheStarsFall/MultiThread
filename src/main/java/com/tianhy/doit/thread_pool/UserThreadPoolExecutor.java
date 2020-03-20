package com.tianhy.doit.thread_pool;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @Author: thy
 * @Date: 2020/2/27 4:08
 * @Desc:
 */
public class UserThreadPoolExecutor extends ThreadPoolExecutor {
    private ConcurrentHashMap<String, Date> startTime;

    public UserThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public UserThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public UserThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public UserThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        this.startTime = new ConcurrentHashMap<>();
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
        System.out.println("总耗时：" + (diff / 1000) + "s");
        System.out.println("任务总数量：" + this.getTaskCount());
        super.afterExecute(r, t);
    }

    @Override
    public void shutdown() {
        System.out.println("已经执行的任务数量 ：" + this.getCompletedTaskCount());
        super.shutdown();
    }

    public static ExecutorService executors(
            int coreSize, int maxSize, long aliveTime, TimeUnit unit,
            BlockingQueue<Runnable> queue, UserThreadFactory factory, UserRejectHandler handler) {
        return new UserThreadPoolExecutor(
                coreSize, maxSize, aliveTime, unit, queue,
                //线程工厂
                factory,
                //决绝策略
                handler);
    }
}
