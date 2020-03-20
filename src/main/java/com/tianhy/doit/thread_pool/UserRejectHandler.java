package com.tianhy.doit.thread_pool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: thy
 * @Date: 2020/2/27 4:30
 * @Desc: 拒绝策略
 */
public class UserRejectHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("Task rejected. " + executor.toString());
    }
}
