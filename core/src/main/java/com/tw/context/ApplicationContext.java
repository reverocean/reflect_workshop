package com.tw.context;

/**
 * User: Haiyang
 * Date: 3/18/13
 * Time: 1:06 PM
 */
public interface ApplicationContext {
    <T> T getBean(String beanName);
}
