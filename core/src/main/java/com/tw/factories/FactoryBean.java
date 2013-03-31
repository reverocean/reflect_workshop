package com.tw.factories;

/**
 * User: Haiyang
 * Date: 3/31/13
 * Time: 10:01 PM
 */
public interface FactoryBean<T> {
    T getObject();
}
