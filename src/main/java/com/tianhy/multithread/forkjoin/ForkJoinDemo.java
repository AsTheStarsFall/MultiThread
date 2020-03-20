package com.tianhy.multithread.forkjoin;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/11
 **/
@Slf4j
public class ForkJoinDemo extends RecursiveTask<Integer> {

    private int start;
    private int end;

    public ForkJoinDemo(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        //声明计算用的变量
        int m = 2000, s = start, n = end, r = 0;
        //创建字任务列表，每个子任务处理m个数字
        List<ForkJoinTask<Integer>> taskList = new ArrayList<ForkJoinTask<Integer>>();

        do{


        }while (s<end);
        return null;
    }
}
