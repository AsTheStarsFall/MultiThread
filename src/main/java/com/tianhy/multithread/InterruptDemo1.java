package com.tianhy.multithread;

import java.util.concurrent.TimeUnit;

/**
 * @Desc: 线程复位可以用来实现多个线程之间的通信
 * @Author: thy
 * @CreateTime: 2018/11/16
 **/
public class InterruptDemo1 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(()->{
            while(true){
                boolean ii= Thread.currentThread().isInterrupted();
                if(ii){
                    System.out.println("before:"+ii);
                    //对线程进行复位，中断标识为false
                    Thread.interrupted();
                    System.out.println("after:"+ Thread.currentThread().isInterrupted());

                }
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
    }
}
