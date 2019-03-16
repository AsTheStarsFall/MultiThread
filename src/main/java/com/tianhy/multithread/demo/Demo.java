package com.tianhy.multithread.demo;

/**
 * @Desc:
 * @Author: thy
 * @CreateTime: 2018/11/16
 **/
public class Demo {

    PrintProcess printProcess;

    protected Demo() {
        SaveProcess saveProcess = new SaveProcess();
        saveProcess.start();
        printProcess = new PrintProcess(saveProcess);
        printProcess.start();
    }

    private void doTest(Request request) {
        printProcess.processResuest(request);
    }

    public static void main(String[] args) {
        Thread thread=new Thread();
        thread.getState();
        Request request=new Request();
        request.setName("thy");
        new Demo().doTest(request);
    }
}
