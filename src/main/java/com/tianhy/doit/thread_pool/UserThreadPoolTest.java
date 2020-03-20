package com.tianhy.doit.thread_pool;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author: thy
 * @Date: 2020/2/27 4:36
 * @Desc:
 */
public class UserThreadPoolTest {

    public static void main(String[] args) {
        //将缓存队列固定值设为2，触发reject
        BlockingQueue queue = new LinkedBlockingQueue(2);
        UserThreadFactory f1 = new UserThreadFactory("第1号机房");
        UserThreadFactory f2 = new UserThreadFactory("第2号机房");

        UserRejectHandler userRejectHandler = new UserRejectHandler();
        ExecutorService service1 = UserThreadPoolExecutor.executors(
                //核心线程数为1，最大线程数为2
                1, 2, 60, TimeUnit.SECONDS, queue, f1, userRejectHandler);
        ExecutorService service2 = UserThreadPoolExecutor.executors(
                1, 2, 60, TimeUnit.SECONDS, queue, f2, userRejectHandler);

        retry:
        for (int i = 0; i < 100; i++) {
            service1.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            service2.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            break retry;
        }
        service1.shutdown();
        service2.shutdown();
    }
}
