package com.tianhy.multithread;

import java.util.concurrent.TimeUnit;

/**
 * @Desc:
 * @Author: thy
 * @CreateTime: 2018/11/16
 **/
public class InterruptDemo {

    private static int i;

    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(()->{
           while(!Thread.currentThread().isInterrupted()){
                i++;
           }
            System.out.println("num:"+i);

        },"interruptDemo");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
    }
}
