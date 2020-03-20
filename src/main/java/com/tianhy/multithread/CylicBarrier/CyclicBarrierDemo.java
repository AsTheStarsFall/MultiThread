package com.tianhy.multithread.CylicBarrier;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * {@link}
 *
 * @Desc: 栅栏阻塞器
 * 可重用的线程阻塞器，通过调用其await()方法在代码中形成一个栅栏，
 * 率先到达的线程将会被阻塞，直到指定数量的线程都到达栅栏处。其构造参数是指定阻塞线程数量。
 * @Author: thy
 * @CreateTime: 2019/5/12
 **/
@Slf4j
public class CyclicBarrierDemo implements Runnable {

    //指定阻塞5个线程
    private static final CyclicBarrier CYCLIC_BARRIER = new CyclicBarrier(5);
    private int count;

    public CyclicBarrierDemo(int count) {
        this.count = count;
    }


    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            try {
                Thread.sleep(1000L + count * 1000L);
                log.info("{} waiting threads", CYCLIC_BARRIER.getNumberWaiting());
                //阻塞，直到指定数量的线程执行到此处
                CYCLIC_BARRIER.await(10L, TimeUnit.SECONDS);
                log.info("continue...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        //定义容量为5的线程池
        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            CyclicBarrierDemo cyclicBarrierDemo = new CyclicBarrierDemo(5);
            es.execute(cyclicBarrierDemo);
        }
        es.shutdown();
    }
}
