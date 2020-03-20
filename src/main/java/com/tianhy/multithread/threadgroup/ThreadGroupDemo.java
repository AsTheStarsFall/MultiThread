package com.tianhy.multithread.threadgroup;

import com.tianhy.multithread.base.ThreadDemo;

/**
 * {@link}
 *
 * @Desc: 线程组
 * @Author: thy
 * @CreateTime: 2019/9/9 9:52
 **/
public class ThreadGroupDemo implements Runnable{


    public static void main(String[] args) {
        ThreadGroup tg = new ThreadGroup("PrintGroup");
        Thread t1 = new Thread(tg,new ThreadDemo(),"T1");
        Thread t2 = new Thread(tg,new ThreadDemo(),"T2");
        t1.start();
        t2.start();

        //获取活动线程总数
        System.out.println("active count:"+tg.activeCount());
        //打印线程组中所有线程信息
        tg.list();
    }

    @Override
    public void run() {
        String groupAndName = Thread.currentThread().getThreadGroup().getName()+"-"+Thread.currentThread().getName();
        while (true){
            System.out.println(groupAndName);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
