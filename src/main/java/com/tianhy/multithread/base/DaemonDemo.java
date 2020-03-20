package com.tianhy.multithread.base;

/**
 * {@link}
 *
 * @Desc: 守护线程
 * 比如垃圾回收线程、JIT线程，与之相对应是用户线程，用户线程可以认为是系统的工作线程。
 * 如果用户线程全部结束，那么意味着程序实际上无事可做，守护线程守护的对象已经不存在了，那么应用程序就应该结束。，
 * 当一个Java应用内只有守护线程时，JVM就会自然退出
 * @Author: thy
 * @CreateTime: 2019/10/10 6:19
 **/
public class DaemonDemo extends Thread{

    @Override
    public void run() {
        while(true){
            System.out.println("i am alive");
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new DaemonDemo();
        //线程被设置为守护线程
        t.setDaemon(true);
        t.start();

        //线程被设置为守护线程，系统中只有main线程为用户线程，所以在休眠2秒后退出
        Thread.sleep(2000L);
    }
}
