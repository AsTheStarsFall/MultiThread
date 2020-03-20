package com.tianhy.multithread.condition;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.*;

/**
 * {@link}
 *
 * @Desc: 让某些线程一起等待（阻塞）某个条件，当条件满足，唤醒线程
 * @Author: thy
 * @CreateTime: 2019/5/12
 **/
@Slf4j
public class ConditionTest implements Runnable {

    //公平锁，将当前线程添加到Ccondition的挂起线程队列尾部
    private static final Lock LOCK = new ReentrantLock(true);
    private static final Condition CONDITION1 = LOCK.newCondition();
    private static final Condition CONDITION2 = LOCK.newCondition();
    private boolean flag;

    public ConditionTest(boolean flag) {
        this.flag = flag;
    }


    @Override
    public void run() {
        LOCK.lock();
        try {
            if (flag) {
                log.info("Before CONDITION1.await() ");
                CONDITION1.await();
                log.info("After CONDITION1.await(); ");

            } else {
                log.info("Before CONDITION2.await(); ");
                CONDITION2.await();
                log.info("After CONDITION1.await(); ");

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(new ConditionTest(true));
        es.execute(new ConditionTest(false));
        es.execute(new Runnable() {
            @Override
            public void run() {
                boolean b = false;
                LOCK.lock();
                try {
                    Thread.sleep(1000L);
                    log.info("CONDITION1.signalAll()");
                    CONDITION1.signalAll();
                    if (b) {
                        Thread.sleep(2000L);
                        log.info("CONDITION2.signalAll()");
                        CONDITION2.signalAll();
                        return;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    LOCK.unlock();
                }

                try {
                    Thread.sleep(2000L);
                    log.info("CONDITION2.signalAll()");
                    CONDITION2.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    LOCK.unlock();
                }
            }
        });
    }
}
