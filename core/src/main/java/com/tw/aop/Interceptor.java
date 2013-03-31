package com.tw.aop;

import java.lang.reflect.Method;

/**
 * User: Haiyang
 * Date: 3/31/13
 * Time: 10:03 PM
 */
public interface Interceptor {
    Object[] before(Method method, Object[] objects);
    Object after(Method method, Object[] objects, Object returnValue);
    void exception(Throwable e) throws Throwable;
}
