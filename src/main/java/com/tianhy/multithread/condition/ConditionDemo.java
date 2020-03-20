package com.tianhy.multithread.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Desc:
 * @Author: thy
 * @CreateTime: 2018/11/21
 **/
public class ConditionDemo implements Runnable {

    private Lock lock;
    private Condition condition;

    public ConditionDemo(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }


    @Override
    public void run() {
        System.out.println("start to condition await");
        try {
            lock.lock();
            condition.await(); //把当前线程阻塞
            System.out.println("end condition await");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }


    }
}
