package com.tianhy.multithread.demo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Desc:
 * @Author: thy
 * @CreateTime: 2018/11/16
 **/
@Setter
@Getter
public class Request {
    private String name;

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                '}';
    }
}
