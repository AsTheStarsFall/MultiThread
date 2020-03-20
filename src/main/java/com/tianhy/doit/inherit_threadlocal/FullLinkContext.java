package com.tianhy.doit.inherit_threadlocal;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author: thy
 * @Date: 2020/2/27 20:11
 * @Desc:
 */
public class FullLinkContext {

    private String traceId;

    public String getTraceId() {
        if(StringUtils.isEmpty(traceId)){

        }
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }
}
