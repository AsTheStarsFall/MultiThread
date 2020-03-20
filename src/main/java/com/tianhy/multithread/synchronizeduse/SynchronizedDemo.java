package com.tianhy.multithread.synchronizeduse;

/**
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/4/1
 **/
public class SynchronizedDemo {

    Object lock = new Object();

    public void demo(){
//        synchronized (lock){ //指定对象的锁
//
//        }

        //当前实例对象的锁
        synchronized (this){

        }

//        synchronized (SynchronizedDemo.class){ //当前类对象锁
//
//        }

    }

    //修饰静态方法，当前类对象锁
    public static synchronized void dothings(){

    }


    public static void main(String[] args) {
        SynchronizedDemo synchronizedDemo = new SynchronizedDemo();


        synchronizedDemo.demo();
    }
}
