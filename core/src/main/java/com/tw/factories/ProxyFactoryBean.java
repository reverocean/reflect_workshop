package com.tw.factories;

import com.tw.context.ApplicationContext;
import com.tw.context.ApplicationContextAware;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * User: Haiyang
 * Date: 3/31/13
 * Time: 10:02 PM
 */
public class ProxyFactoryBean<T> implements FactoryBean, InvocationHandler, ApplicationContextAware {
    private Object target;
    private List<String> interfaces;
    private List<String> interceptorNames;
    private ApplicationContext applicationContext;

    @Override
    public T getObject() {
        throw new UnsupportedOperationException("Need to implement");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] objects) throws Throwable {
        throw new UnsupportedOperationException("Need to implement");
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public void setInterfaces(List<String> interfaces) {
        this.interfaces = interfaces;
    }

    public void setInterceptorNames(List<String> interceptorNames) {
        this.interceptorNames = interceptorNames;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
