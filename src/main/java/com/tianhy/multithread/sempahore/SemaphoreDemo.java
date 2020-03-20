package com.tianhy.multithread.sempahore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Desc: 信号灯，可以控制同时访问的线程个数
 * @Author: thy
 * @CreateTime: 2018/11/21
 **/
public class SemaphoreDemo {


    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 10; i++) {
            new Car(i, semaphore).start();
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
                System.out.println("第" + num + "辆车占用一个车位");
                TimeUnit.SECONDS.sleep(2);
                //release()释放一个许可
                semaphore.release();
                System.out.println("第" + num + "辆车走了");

                /**
                 * 控制访问资源的线程个数，当信号量大于1说明有资源可以访问，获取许可，计数器-1，访问资源
                 * 当计数器为0时，阻塞，直到某个线程访问完资源并释放信号量，计数器+1
                 */

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
