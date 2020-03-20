package com.tianhy.multithread.printabc;

import lombok.Data;

import java.util.concurrent.*;

/**
 * {@link}
 *
 * @Desc: synchronized + wait() + notifyAll() + OperateInteger
 * Integer 不能作为synchronized 的锁对象，因为修改Integer的值会改变它的引用
 * @Author: thy
 * @CreateTime: 2019/5/11
 **/
public class SynAbc implements Runnable {

    //自定义锁对象OperateInteger
    private static OperateInteger operateInteger = new OperateInteger(0, 30);
    private static String[] chars = {"a", "b", "c"};

    private String name;

    public SynAbc(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (operateInteger.getCurrentCount() < operateInteger.getMaxCount()) {
            synchronized (operateInteger) {
                if (operateInteger.getCurrentCount() < operateInteger.getMaxCount()) {
                    if (this.name.equals(chars[operateInteger.getCurrentCount() % 3])) {
                        operateInteger.printAndPlusOne();
                        operateInteger.notifyAll();
                    } else {
                        try {
                            operateInteger.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,
                3, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        threadPoolExecutor.execute(new SynAbc(chars[0]));
        threadPoolExecutor.execute(new SynAbc(chars[1]));
        threadPoolExecutor.execute(new SynAbc(chars[2]));
        threadPoolExecutor.shutdown();
    }


    @Data
    private static class OperateInteger {
        private int currentCount;
        private int maxCount;

        public OperateInteger(int currentCount, int maxCount) {
            this.currentCount = currentCount;
            this.maxCount = maxCount;
        }

        private void printAndPlusOne() {
            System.out.println(chars[currentCount % 3]);
            ++currentCount;
        }
    }
}
