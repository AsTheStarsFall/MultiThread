package com.tianhy.multithread;

import java.util.concurrent.CountDownLatch;

/**
 * @Desc: CountDownLatch 它允许一个或多个线程一直等待，直到其他线程的操作执行完毕再执行
 * @Author: thy
 * @CreateTime: 2018/11/21
 **/
public class CDLDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cdl= new CountDownLatch(3);

        new Thread(()->{

            cdl.countDown();
            System.out.println("t1 start");
        },"t1").start();
        new Thread(()->{

            cdl.countDown();
            System.out.println("t2 start");
        },"t2").start();
        new Thread(()->{

            cdl.countDown();
            System.out.println("t3 start");

        },"t3").start();

        cdl.await();
        System.out.println("执行完毕");
    }


}
