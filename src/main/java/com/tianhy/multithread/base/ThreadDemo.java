package com.tianhy.multithread.base;

import java.util.Random;

/**
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/3/23
 **/
public class ThreadDemo extends Thread{
    /**
     * 写的时候最好要设置线程名称 Thread.name，并设置线程组 ThreadGroup，
     * 目的是方便管理。在出现问题的时候，打印线程栈 (jstack -pid) 一眼就可以看出是哪个线程出的问题，这个线程是干什么的。
     */

    public static void main(String[] args) {
        try {
            Thread t1 = new Thread(new Task());
            t1.setUncaughtExceptionHandler((t, e) -> System.out.println("catch erro info"));
            t1.start();
        }catch (Exception e){
            System.out.println("unable catch erro info");
        }
        new Random().nextInt();
        //不能用try,catch来获取线程中的异常
    }


}
