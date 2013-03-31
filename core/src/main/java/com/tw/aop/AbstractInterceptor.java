package com.tw.aop;

import java.lang.reflect.Method;

/**
 * User: Haiyang
 * Date: 3/31/13
 * Time: 10:33 PM
 */
public abstract class AbstractInterceptor implements Interceptor {
    @Override
    public Object[] before(Method method, Object[] objects) {
        return objects;
    }

    @Override
    public Object after(Method method, Object[] objects, Object returnValue) {
        return returnValue;
    }

    @Override
    public void exception(Throwable e) throws Throwable {
        throw e;
    }
}
