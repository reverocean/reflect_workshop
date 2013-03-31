package com.tw.interceptors;

import com.tw.aop.AbstractInterceptor;

import java.lang.reflect.Method;
import java.util.List;

import static com.google.common.collect.ImmutableList.of;

/**
 * User: Haiyang
 * Date: 3/31/13
 * Time: 10:48 PM
 */
public class QueryValidateInterceptor extends AbstractInterceptor {
    private List<String> forbiddenAccounts = of("Laden", "Zhengri");
    @Override
    public Object after(Method method, Object[] objects, Object returnValue) {
        if(method.getName().contains("query")){
            String account = (String) objects[0];
            if (forbiddenAccounts.contains(account)) {
                return -1.0;
            }
        }
        return returnValue;
    }
}
