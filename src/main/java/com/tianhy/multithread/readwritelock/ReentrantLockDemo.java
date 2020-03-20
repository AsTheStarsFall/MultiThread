package com.tianhy.multithread.readwritelock;



import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Desc: ReentrantLock重入锁
 *        非公平锁和公平锁
 *        先对锁进行获取请求的一定被先满足获得锁，称为公平锁，反之就是非公平锁
 * @Author: thy
 * @CreateTime: 2018/11/20
 **/
public class ReentrantLockDemo {

    private static int count = 0;

    static Lock lock = new ReentrantLock();

    public static void inc () throws InterruptedException {
        //获取锁后，再次调用lock()不会阻塞，直接增加重试次数
        lock.lock();

        //可以被打断，当调用 interrupy() 会退出阻塞并放弃对cpu的争夺
        //lock.lockInterruptibly();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i =0;i<1000;i++){
            new Thread(()->{
                try {
                    ReentrantLockDemo.inc();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(3000);
        System.out.println("result:"+count);

    }


}
