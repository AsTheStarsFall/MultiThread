package com.tianhy.multithread;

import java.util.concurrent.*;

/**
 * @Desc:
 * @Author: thy
 * @CreateTime: 2018/11/16
 **/
public class CallableDemo implements Callable<String> {

    /**
     * 有的时候，我们可能需要让一步执行的线程在执行完成以后，
     * 提供一个返回值 给到当前的主线程，主线程需要依赖这个值进行后续的逻辑处理，
     * 那么这个时候，就需要用到带返回值的线程了
     */
    public static void main(String[] args) {

        try {
            ExecutorService executorService= Executors.newFixedThreadPool(1);
            CallableDemo callableDemo=new CallableDemo();

            Future<String> future=executorService.submit(callableDemo);

            System.out.println("print : "+future.get()); // 阻塞

            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String call() throws Exception {
        int a =1;
        int b =2;
        System.out.println(a+b);
        return "结果为："+(a+b);
    }
}
