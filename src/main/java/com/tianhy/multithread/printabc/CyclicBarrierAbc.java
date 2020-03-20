package com.tianhy.multithread.printabc;

import java.util.concurrent.*;

/**
 * {@link}
 *
 * @Desc: CylicBarrier 可重用的线程阻塞器
 * @Author: thy
 * @CreateTime: 2019/5/11
 **/
public class CyclicBarrierAbc implements Runnable {
    //阻塞3个线程
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
    private static Integer currentCount = 0;
    private final Integer MAX_COUNT = 30;
    private static String[] chars = {"a", "b", "c"};

    private String name;

    public CyclicBarrierAbc(String name) {
        this.name = name;
    }


    @Override
    public void run() {
        while (currentCount < MAX_COUNT) {
            while (this.name.equals(chars[currentCount % 3])) {
                printAndIncrement(this.name);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void printAndIncrement(String s) {
        System.out.println(s);
        ++currentCount;
    }

    public static void main(String[] args) {
        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(3,
                3,
                10,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>());
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3, 10,
//              TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        threadPoolExecutor.execute(new CyclicBarrierAbc(chars[0]));
        threadPoolExecutor.execute(new CyclicBarrierAbc(chars[1]));
        threadPoolExecutor.execute(new CyclicBarrierAbc(chars[2]));
        threadPoolExecutor.shutdown();
    }
}
