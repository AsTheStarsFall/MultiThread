package com.tianhy.multithread;

import java.util.concurrent.TimeUnit;

/**
 * @Desc: Volatile 的可见性
 * @Author: thy
 * @CreateTime: 2018/11/16
 **/
public class VolatileDemo {
    private static volatile boolean stop = false;

    public static void main(String[] args) throws InterruptedException {

        Thread thread=new Thread(()->{
            int i = 0;
           while (!stop){
                i++;

           }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(100);
        stop = true;
    }
}
