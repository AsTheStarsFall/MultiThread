package com.tianhy.doit.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * @Author: thy
 * @Date: 2020/2/27 18:14
 * @Desc: ThreadLocal不能解决共享变量更新的问题
 */
public class InitValueInThreadLocal {
    private static final StringBuilder INIT_VALUE = new StringBuilder("init");

    //ThreadLocal初始化，返回StringBuilder的引用
    private static final ThreadLocal<StringBuilder> builder =
            new ThreadLocal<StringBuilder>() {
                @Override
                protected StringBuilder initialValue() {
                    return INIT_VALUE;
                }
            };

    private static class AppendStringThread extends Thread {
        @Override
        public void run() {
            //使用某个引用来操作共享对象
            StringBuilder inThread = builder.get();
            for (int i = 0; i < 10; i++) {
                inThread.append("-" + i);
            }
            inThread.append("\n");
            //输出的结果为乱序
            System.out.println(inThread.toString());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new AppendStringThread().start();
        }
        TimeUnit.SECONDS.sleep(10);
    }
}

