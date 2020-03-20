package com.tianhy.multithread.base;

/**
 * @Desc: Thread相关方法
 * @Author: thy
 * @CreateTime: 2019/3/23
 **/
public class BaseMethod {
    public static void main(String[] args) throws InterruptedException {
        //当前线程可转让cpu控制权，让别的就绪状态线程运行（切换）
        Thread.yield();
        //暂停一段时间
        Thread.sleep(1000);
        //在一个线程中调用other.join(),将等待other执行完后才继续本线程。　　

        /**
         * interrupte(),被打断的线程会抛出InterruptedException
         * Thread.interrupted()检查当前线程是否发生中断，返回boolean
         *
         */

    }

}
