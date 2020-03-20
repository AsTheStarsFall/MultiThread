package com.tianhy.multithread.printabc;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * {@link}
 *
 * @Desc: Lock unclok
 * @Author: thy
 * @CreateTime: 2019/5/11
 **/
public class LockAbc implements Runnable{
    //可重入锁
    private static Lock lock = new ReentrantLock();

    private static Integer currentCount = 0;
    private static final Integer MAX_COUNT = 30;
    private static String[] chars = {"a", "b", "c"};

    private String name;

    public LockAbc(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (currentCount < MAX_COUNT){

            //lock/unclock 与try{}catch(){}finally{} 配合使用，避免出现异常后不释放锁
            try {
                lock.lock();
                while (this.name.equals(chars[currentCount%3])&& currentCount < MAX_COUNT){
                    printAndIncrement(name);
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
    public void printAndIncrement(String s) {
        System.out.println(s);
        ++currentCount;
    }

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,
                3, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        threadPoolExecutor.execute(new LockAbc(chars[0]));
        threadPoolExecutor.execute(new LockAbc(chars[1]));
        threadPoolExecutor.execute(new LockAbc(chars[2]));
        threadPoolExecutor.shutdown();
    }
}
