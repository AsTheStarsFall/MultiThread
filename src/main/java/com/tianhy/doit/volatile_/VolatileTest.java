package com.tianhy.doit.volatile_;


/**
 * @Author: thy
 * @Date: 2020/2/27 0:46
 * @Desc:
 */
public class VolatileTest {

    public static void main(String[] args) {
        SubtractThread subtractThread = new SubtractThread();
        subtractThread.start();

        for (int i = 0; i < NUMBER; i++) {
            count++;
        }
        /**
         *         //读取count的值，并压入操作栈栈顶
         *         22: getstatic     #1                  // Field count:I
         *         //常量1压如操作栈
         *         25: iconst_1
         *         //将栈顶两个元素相加
         *         26: iadd
         *         //将运算结果赋值给 count
         *         27: putstatic     #1                  // Field count:I
         */
        while (subtractThread.isAlive()) {

        }

        //执行结果基本上不为0
        System.out.println("count=" + count);

        for (; ; ) {

        }




    }

    public static void disorder() {
        //指令重排序
        int x = 1;
        int y = 2;
        int z = 3;
        x = x + 1;
        int sum = x + y + z;
    }

    //可见性
    private static volatile int count = 0;
    private static final int NUMBER = 10000;

    private static class SubtractThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < NUMBER; i++) {
                count--;
            }
        }
    }

}
