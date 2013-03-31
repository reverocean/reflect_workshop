package com.tw.interceptors;

import com.tw.aop.AbstractInterceptor;

import java.lang.reflect.Method;

/**
 * User: Haiyang
 * Date: 3/31/13
 * Time: 10:48 PM
 */
public class BonusInterceptor extends AbstractInterceptor {
    @Override
    public Object[] before(Method method, Object[] objects) {
        if (method.getName().contains("deposit")) {
            Double money = (Double) objects[1];
            if (money > 100.0) {
                return new Object[]{objects[0], (money + 5)};
            }
        }
        return objects;
    }
}
