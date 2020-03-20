package com.tianhy.multithread.countdownlatch;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Desc: CountDownLatch 它允许一个或多个线程一直等待，直到其他线程的操作执行完毕再执行
 * @Author: thy
 * @CreateTime: 2018/11/21
 **/
@Slf4j
public class CDLDemo {
    public static void main(String[] args) throws InterruptedException {

        int count = 200;
        final CountDownLatch cdl = new CountDownLatch(count);

        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 0; i < count; i++) {
            es.execute(new Runnable() {
                @Override
                public void run() {
                    try {
//                        Thread.sleep(1000L);
                        log.info(String.format("计数器：%d",cdl.getCount()));
                    }
//                    catch (InterruptedException e){
//                        e.printStackTrace();
//                    }
                    finally {
                        cdl.countDown();
                    }
                }
            });
        }

        try {
            //阻塞当前线程，直到计数器为0，超时限制为10秒
            cdl.await(10L,TimeUnit.SECONDS);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        es.shutdown();
        log.info(String.format("计数器：%d",cdl.getCount()));


//        new Thread(() -> {
//
//            cdl.countDown();
//            System.out.println("t1 start");
//        }, "t1").start();
//        new Thread(() -> {
//
//            cdl.countDown();
//            System.out.println("t2 start");
//        }, "t2").start();
//        new Thread(() -> {
//
//            cdl.countDown();
//            System.out.println("t3 start");
//
//        }, "t3").start();
//
//        cdl.await();
//        System.out.println("执行完毕");
    }


}
