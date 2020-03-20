package com.tianhy.doit.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author: thy
 * @Date: 2020/2/27 1:54
 * @Desc: 信号量同步之 CountDownLatch
 */
public class CountDownLatchTest {


    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Thread t1 = new Test("1st", countDownLatch);
/*        Thread t2 = new Test("2st", countDownLatch);
        Thread t3 = new Test("3st", countDownLatch);*/

        //设置异常handler，捕获子线程抛出的异常
        t1.setUncaughtExceptionHandler((Thread thread, Throwable e) -> {
            System.out.println(thread.getName() + "," + e.getMessage());
        });
        t1.start();
/*        t2.start();
        t3.start();*/
        try {
            countDownLatch.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("done");


    }

    static class Test extends Thread {
        private String content;
        private final CountDownLatch countDownLatch;

        public Test(String content, CountDownLatch countDownLatch) {
            this.content = content;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            //某种情况下发生了异常
            if (Math.random() > 0.5) {
                throw new RuntimeException("Exception");
            }
            System.out.println(content);
            //调用release，使数值递减，当数值为0时，解除所有线程的阻塞
            countDownLatch.countDown();
        }
    }

}
