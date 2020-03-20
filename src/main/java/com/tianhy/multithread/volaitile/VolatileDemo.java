package com.tianhy.multithread.volaitile;

import java.util.concurrent.TimeUnit;

/**
 * @Desc: VolatilePrintAbc 的可见性
 * @Author: thy
 * @CreateTime: 2018/11/16
 **/
public class VolatileDemo {

    /**volatile关键字修饰标识符，上个线程做出的修改对于下个访问的线程来说是是可见的*/
    private volatile static boolean stop = false;

    public static void main(String[] args) throws InterruptedException {

        Thread thread=new Thread(()->{
            int i = 0;
            System.out.println(stop);
            // stop 主线程修改，子线程访问，可见性
           while (!stop){
                i++;
           }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(100);
        //main线程修改标识符，如果不加volatile关键字修饰stop标识，上面的线程会进入死循环
        stop = true;
    }
}
