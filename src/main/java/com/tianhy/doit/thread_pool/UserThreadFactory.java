package com.tianhy.doit.thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: thy
 * @Date: 2020/2/27 3:48
 * @Desc: 自定义线程工厂
 */
public class UserThreadFactory implements ThreadFactory {

    //名称前缀
    private final String namePrefix;
    private final AtomicInteger nextId = new AtomicInteger(1);
    private final ThreadGroup threadGroup;

    //定义线程组名称
    public UserThreadFactory(String featureNameOfGroup) {
        SecurityManager securityManager = System.getSecurityManager();
        threadGroup = (securityManager != null) ? securityManager.getThreadGroup()
                : Thread.currentThread().getThreadGroup();
        this.namePrefix = "UserThreadFactory's " + featureNameOfGroup + "-Worker-";
    }

    @Override
    public Thread newThread(Runnable r) {
        String name = namePrefix + nextId.getAndIncrement();
        Thread thread = new Thread(threadGroup, r, name, 0);
        System.out.println(thread.getName());
        return thread;
    }

}
