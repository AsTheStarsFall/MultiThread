package com.tianhy.multithread.volaitile;

/**
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/4/5
 **/
public class SingletonVolatile {

    /**volatile关键字在汇编中会生成一个lock指令，这个指令会触发总线锁，根据缓存MESI协议也就是缓存一致性协议
     * lock指令会将cpu的缓存回写到主内存中，这样会导致其他cpu的缓存无效，直接从主内存读取，所以对其他线程来说是可见的
     * */
    private static volatile SingletonVolatile singletonVolatile = null;

    public static SingletonVolatile getInstance(){
        if(singletonVolatile == null){
            singletonVolatile = new SingletonVolatile();
        }
        return singletonVolatile;
    }

    public static void main(String[] args) {
        SingletonVolatile.getInstance();
    }
}
