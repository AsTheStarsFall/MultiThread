package com.tianhy.multithread.requestProcessDemo;

import lombok.extern.java.Log;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Desc:
 * @Author: thy
 * @CreateTime: 2018/11/16
 **/
@Log
public class SaveProcess extends Thread implements RequestProcess{

    LinkedBlockingQueue<Request> requests=new LinkedBlockingQueue<>();

    @Override
    public void run() {
        while (true){
            try {
                Request request=requests.take();
                System.out.println("begain save request:"+request);
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
