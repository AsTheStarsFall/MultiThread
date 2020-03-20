package com.tianhy.multithread.base;


/**
 * @Desc: 原子性
 * @Author: thy
 * @CreateTime: 2019/4/6
 **/
public class AtomicTest {

    private static int count = 0;

    private synchronized static void incr(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
    }

    public static void main(String[] args) {

        // 多个线程同时修改一个变量，无法实现原子性，必须通过synchronzied来修饰保证原子性
        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                AtomicTest.incr();
            },"thread-"+i).start();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count);

    }
}
