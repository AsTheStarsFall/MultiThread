package com.tianhy.multithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Desc:
 * @Author: thy
 * @CreateTime: 2018/11/21
 **/
public class ConditionSignalDemo implements Runnable {

    private Lock lock;
    private Condition condition;

    @Override
    public void run() {
        System.out.println("start to condition await");
        try {
            lock.lock();
            condition.signal();//唤醒阻塞的线程
            System.out.println("end condition await");
        }finally {
            lock.unlock();
        }
    }
}
