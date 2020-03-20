package com.tianhy.multithread.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Desc: 线程工厂
 * @Author: thy
 * @CreateTime: 2019/4/8
 **/
public class MyThreadFactory implements ThreadFactory {


    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;


    MyThreadFactory() {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
        //线程组名称
        namePrefix = "pool-" +
                poolNumber.getAndIncrement() +
                "-thread-";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r,
                namePrefix + threadNumber.getAndIncrement(),
                0);
        //守护线程
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        //优先级
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }

    public static void main(String[] args) {
        SecurityManager s = System.getSecurityManager();
        System.out.println(s.getThreadGroup());
    }
}
