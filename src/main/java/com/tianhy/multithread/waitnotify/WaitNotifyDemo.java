package com.tianhy.multithread.waitnotify;

/**
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/4/1
 **/
public class WaitNotifyDemo {
    final static Object object = new Object();

    public static class Thread1 extends Thread {
        private String name;

        public Thread1(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            synchronized (object) {
                System.out.println(name + " strat...");
                try {
                    System.out.println(name + " wait 5 sec...");
                    /**
                     *如果一个线程调用了wait()方法，它会进入这个object对象的等待队列。在这个队列中可能会有多个线程，
                     * 因为运行多个线程同时等待某个对象
                     */
                    //t1线程wait后，t2线程会获取cpu
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + " end");
            }

        }
    }

    public static class Thread2 extends Thread {
        private String name;

        public Thread2(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            synchronized (object) {
                System.out.println(name + " start! notify: t1");
                /**
                 * 当object的notify()方法被调用时，它就会从object对象的等待队列中随机选择一个线程，并将其唤醒
                 */
                object.notify();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + " end");
            }
        }
    }

    public static void main(String[] args) {
        Thread1 t1 = new Thread1("t1");
        Thread2 t2 = new Thread2("t2");
        t1.start();
        t2.start();
    }
}
