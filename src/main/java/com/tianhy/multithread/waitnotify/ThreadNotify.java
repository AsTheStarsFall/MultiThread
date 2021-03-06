package com.tianhy.multithread.waitnotify;

/**
 * @Desc:
 * @Author: thy
 * @CreateTime: 2018/11/20
 **/
public class ThreadNotify extends Thread {
    private Object lock;

    public ThreadNotify(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock){
            System.out.println("notify start");
            lock.notify();
            System.out.println("notify end");

        }
    }
}
