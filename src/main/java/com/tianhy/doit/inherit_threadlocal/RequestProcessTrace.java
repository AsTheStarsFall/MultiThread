package com.tianhy.doit.inherit_threadlocal;

import java.text.SimpleDateFormat;

/**
 * @Author: thy
 * @Date: 2020/2/27 20:09
 * @Desc: 请求追踪的示例来说明 InheritThreadLocal
 */
public class RequestProcessTrace {

    private static final ThreadLocal<SimpleDateFormat> DATE_FORMAT_THREAD_LOCAL =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    private static final InheritableThreadLocal<FullLinkContext>
            FULL_LINK_CONTEXT_INHERITABLE_THREAD_LOCAL = new InheritableThreadLocal<>();

    public static FullLinkContext getContext() {
        FullLinkContext fullLinkContext = FULL_LINK_CONTEXT_INHERITABLE_THREAD_LOCAL.get();
        if (fullLinkContext == null) {
            FULL_LINK_CONTEXT_INHERITABLE_THREAD_LOCAL.set(new FullLinkContext());
            fullLinkContext = FULL_LINK_CONTEXT_INHERITABLE_THREAD_LOCAL.get();
        }
        return fullLinkContext;
    }

}
