package com.tianhy.multithread.callable;

import java.util.concurrent.*;

/**
 * @Desc: 带返回值的线程
 * @Author: thy
 * @CreateTime: 2018/11/16
 **/
public class CallableDemo implements Callable<String> {

    /**
     * 有的时候，我们可能需要让上一步执行的线程在执行完成以后，
     * 提供一个返回值 给到当前的主线程，主线程需要依赖这个值进行后续的逻辑处理，
     * 那么这个时候，就需要用到带返回值的线程了
     */
    public static void main(String[] args) {

        try {
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            CallableDemo callableDemo = new CallableDemo();
            /**
             * future模式：并发模式的一种，可以有两种形式，即无阻塞和阻塞，分别是isDone和get。
             * 其中Future对象用来存放该线程的返回值以及状态
             */
            Future<String> future = executorService.submit(callableDemo);

            //get()阻塞直到该线程运行结束
            //return 返回值
            System.out.println("print : " + future.get());

            //future.isDone() return true,false 无阻塞
            //System.out.println(future.isDone());

            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String call() throws Exception {
        int a = 1;
        int b = 2;
        System.out.println(a + b);
        return "结果为：" + (a + b);
    }
}
