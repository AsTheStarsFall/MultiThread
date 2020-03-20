package com.tianhy.multithread.printabc;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@link}
 *
 * @Desc: AtomicInteger + CAS
 * @Author: thy
 * @CreateTime: 2019/5/11
 **/
public class CasAbc implements Runnable {
    //原子递增，从0开始
    private static AtomicInteger currentCount = new AtomicInteger(0);
    private final Integer MAX_COUNT = 30;
    private static String[] chars = {"a", "b", "c"};

    private String name;

    public CasAbc(String name) {
        this.name = name;
    }


    @Override
    public void run() {
        while (currentCount.get() < MAX_COUNT) {
            //currentCount.get() % 3 下标为 0 1 2
            if (this.name.equals(chars[currentCount.get() % 3])) {
                printAndIncrement(this.name);
            }
        }
    }

    public void printAndIncrement(String s) {
        System.out.println(s);
        currentCount.getAndIncrement();
    }

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,
                3, 10,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        threadPoolExecutor.execute(new CasAbc(chars[0]));
        threadPoolExecutor.execute(new CasAbc(chars[1]));
        threadPoolExecutor.execute(new CasAbc(chars[2]));
        threadPoolExecutor.shutdown();

    }
}
