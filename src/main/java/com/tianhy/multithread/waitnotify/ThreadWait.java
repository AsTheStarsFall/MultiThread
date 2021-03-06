package com.tianhy.multithread.waitnotify;

/**
 * @Desc:
 * @Author: thy
 * @CreateTime: 2018/11/20
 **/
public class ThreadWait extends Thread {

    private Object lock;

    public ThreadWait(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
         synchronized (lock){
             System.out.println("wait start");
             try {
                 lock.wait();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             System.out.println("wait end");
         }
    }
}
