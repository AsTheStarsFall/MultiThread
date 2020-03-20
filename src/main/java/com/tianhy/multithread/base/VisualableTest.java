package com.tianhy.multithread.base;


import java.util.concurrent.TimeUnit;

/**
 * @Desc: 可见性
 * @Author: thy
 * @CreateTime: 2019/4/6
 **/
public class VisualableTest {

    private volatile static boolean flag = false;
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            int i = 0;
            while (!flag){
                i++;
            }
        },"t1");
        t1.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //主线程修改了被volatile修饰的标识，对子线程可见
        flag = true;

    }
}
