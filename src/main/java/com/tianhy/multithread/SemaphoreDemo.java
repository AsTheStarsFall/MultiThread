package com.tianhy.multithread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Desc: 信号灯，可以控制同时访问的线程个数
 * @Author: thy
 * @CreateTime: 2018/11/21
 **/
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(5);
        for (int i = 0; i < 10; i++){
            new Car(i,semaphore).start();
        }
    }
    static class Car extends Thread {
        private int num;
        private Semaphore semaphore;

        public Car(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                //acquire()获得一个许可
                semaphore.acquire();
                System.out.println("第"+num+"辆车占用一个车位");
                TimeUnit.SECONDS.sleep(2);
                //release()释放一个许可
                semaphore.release();
                System.out.println("第"+num+"辆车走了");

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
