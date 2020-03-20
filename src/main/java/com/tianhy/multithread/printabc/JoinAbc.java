package com.tianhy.multithread.printabc;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/14
 **/
public class JoinAbc {

    public static void main(String[] args) throws InterruptedException {
        /**
         * join()方法的本质是调用wait()方法在当前线程对象的实例上
         */

        Thread t1 = new Thread(() -> {
            System.out.println("a");
        }, "t1");

        Thread t2 = new Thread(() -> {
            System.out.println("b");
        }, "t2");

        Thread t3 = new Thread(() -> {
            System.out.println("c");
        }, "t3");


        t1.start();
        //等待线程1执行完毕
        t1.join();

        t2.start();
        //等待线程2执行完毕
        t2.join();

        t3.start();


    }


}
