package com.tw.config;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;

/**
 * User: Haiyang
 * Date: 3/20/13
 * Time: 12:06 PM
 */
public class ConfigsImpl {
    public List<BeanConfig> getBeanConfigs() {
        BeanConfig bankDaoBeanConfig = new BeanConfig("bankDao", "com.tw.dao.DefaultBankDao");
        BeanConfig bankServiceBeanConfig = new BeanConfig("bankService", "com.tw.service.DefaultBankService");
        bankServiceBeanConfig.addProperty(new BeanProperty("bankDao", "bankDao"));

        return of(bankDaoBeanConfig, bankServiceBeanConfig);
    }

}
