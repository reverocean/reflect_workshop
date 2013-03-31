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
    private Class[] interfaces;
    private List<String> interceptorNames;
    private ApplicationContext applicationContext;

    @Override
    public T getObject() {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), interfaces, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] objects) throws Throwable {
        return null;
    }

    public void setTarget(Object target) {
        this.target = target;
    }


    public void setInterfaces(Class[] interfaces) {
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
