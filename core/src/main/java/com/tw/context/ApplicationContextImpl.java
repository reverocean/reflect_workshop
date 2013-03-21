package com.tw.context;

import com.tw.config.Configs;

/**
 * User: Haiyang
 * Date: 3/18/13
 * Time: 6:51 PM
 */
public class ApplicationContextImpl implements ApplicationContext {
    private Configs configs;

    public ApplicationContextImpl(Configs configs) {
        this.configs = configs;
    }

    @Override
    public <T> T getBean(String beanName) {
        throw new UnsupportedOperationException();
    }
}
