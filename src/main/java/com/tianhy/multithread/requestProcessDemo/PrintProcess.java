package com.tianhy.multithread.requestProcessDemo;

import lombok.extern.java.Log;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Desc: 通过阻塞队列以及多线程的方式，实现对请求的异步化处理，提升处理性能
 * @Author: thy
 * @CreateTime: 2018/11/16
 **/
@Log
public class PrintProcess extends Thread implements RequestProcess{

    private final RequestProcess nextProcess;

    LinkedBlockingQueue<Request> requests=new LinkedBlockingQueue<>();

    public PrintProcess(RequestProcess nextProcess) {
        this.nextProcess = nextProcess;
    }

    @Override
    public void run() {
        while (true){
            try {
                Request request=requests.take();
                System.out.println("print data:"+request.getName());
                nextProcess.processResuest(request);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void processResuest(Request request) {
            requests.add(request);
    }
}
