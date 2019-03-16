package com.tianhy.multithread;


import java.util.concurrent.TimeUnit;

/**
 * @Desc: 线程状态
 * @Author: thy
 * @CreateTime: 2018/11/16
 **/
public class ThreadStatus {

    public static void main(String[] args) {
        //time_waiting
        new Thread(()->{
            while(true){
                try {
                    TimeUnit.SECONDS.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

        },"timewaiting").start();

        //WAITING, 线程在ThreadStatus上锁通过wait()进行等待
        new Thread(()->{
            while(true){
                synchronized (ThreadStatus.class){
                    try {
                        ThreadStatus.class.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        },"waiting").start();
        //线程在 ThreadStatus 加锁后，不会释放锁
        new Thread(new BlockDemo(),"BlockDemo-01").start();
        new Thread(new BlockDemo(),"BlockDemo-02").start();
    }

    static class BlockDemo extends Thread {
        @Override
        public void run() {
            synchronized (BlockDemo.class){
                while(true){
                    try {
                        TimeUnit.SECONDS.sleep(100);

                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }



}
