package com.tianhy.doit.threadlocal;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: thy
 * @Date: 2020/2/27 16:48
 * @Desc: 示例说明ThreadLocal
 */
public class CsGameByThreadLocal {
    private static final Integer BULLET_NUMBER = 1500;
    private static final Integer KILLED_NUMBER = 0;
    private static final Integer LIFE_VALUE = 10;
    private static final Integer TOTAL_PALYERS = 10;

    //随机数用来展示每个对象的不同数据
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    //初始化子弹数
    private static final ThreadLocal<Integer> BULLET_NUMBER_THREADLOCAL
            = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return BULLET_NUMBER;
        }
    };

    //初始化杀敌数
    private static final ThreadLocal<Integer> KILL_NUMBER_THREADLOCAL
            = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return KILLED_NUMBER;
        }
    };

    //初始化命数
    private static final ThreadLocal<Integer> LIFE_VALUE_THREADLOCAL
            = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return LIFE_VALUE;
        }
    };

    //定义每个玩家
    private static class Players extends Thread {
        @Override
        public void run() {
            Integer bullets = BULLET_NUMBER_THREADLOCAL.get() - RANDOM.nextInt(BULLET_NUMBER);
            Integer killEnemies = KILL_NUMBER_THREADLOCAL.get() + RANDOM.nextInt(TOTAL_PALYERS / 2);
            Integer lifeValue = LIFE_VALUE_THREADLOCAL.get() - RANDOM.nextInt(LIFE_VALUE);

            System.out.println(getName() + ",bullets=" + bullets);
            System.out.println(getName() + ",killEnemies=" + killEnemies);
            System.out.println(getName() + ",lifeValue=" + lifeValue);

            BULLET_NUMBER_THREADLOCAL.remove();
            KILL_NUMBER_THREADLOCAL.remove();
            LIFE_VALUE_THREADLOCAL.remove();

        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < TOTAL_PALYERS; i++) {
            new Players().start();
        }
    }
}
