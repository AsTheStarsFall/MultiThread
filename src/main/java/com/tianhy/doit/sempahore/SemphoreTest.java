package com.tianhy.doit.sempahore;

import lombok.SneakyThrows;
import sun.util.locale.provider.TimeZoneNameUtility;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author: thy
 * @Date: 2020/2/26 23:42
 * @Desc: 信号灯
 */
public class SemphoreTest {


    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(10);
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

        @SneakyThrows
        @Override
        public void run() {
            //获取资源 state-1
            semaphore.acquire();
            System.out.println("第" + num + "辆car获取了车位");
            TimeUnit.SECONDS.sleep(2);
            //释放资源 state+1
            semaphore.release();
            System.out.println("第" + num + "辆car开走了");
        }
    }

}
