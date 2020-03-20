package com.tianhy.multithread.volaitile;

/**
 * @Desc: 原子性问题
 * @Author: thy
 * @CreateTime: 2019/4/5
 **/
public class AtomicDemo {

    private static int count = 0;

    public static void inc() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(AtomicDemo::inc).start();
        }
        try {
            //睡眠四秒让循环有充分的时间执行
            Thread.sleep(4000);
            System.out.println(count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
