package com.tianhy.multithread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Desc: 保证原子性的基本类型 AtomicInteger
 * @Author: thy
 * @CreateTime: 2019/4/8
 **/
public class AtomicIntegerDemo {

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static synchronized void inc(){

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //原子递增
        atomicInteger.getAndIncrement();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                AtomicIntegerDemo.inc();
            }).start();
        }

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(atomicInteger.get());
    }
}
