package com.tw.config;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;

/**
 * User: Haiyang
 * Date: 3/20/13
 * Time: 12:06 PM
 */
public class ConfigsImpl implements Configs{
    public List<BeanConfig> getBeanConfigs() {
        return of(createBankDaoBeanConfig(), createBankServiceTargetBeanConfig(), createBankServiceProxyBeanConfig(), createBonusInterceptor(), createValidateInterceptor());
    }

    private BeanConfig createBankDaoBeanConfig() {
        return new BeanConfig("bankDao", "com.tw.dao.DefaultBankDao");
    }

    private BeanConfig createBankServiceTargetBeanConfig() {
        BeanConfig bankServiceBeanConfig = new BeanConfig("bankServiceTarget", "com.tw.service.DefaultBankService");
        bankServiceBeanConfig.addProperty(new BeanProperty("bankDao", "bankDao"));
        return bankServiceBeanConfig;
    }

    private BeanConfig createBankServiceProxyBeanConfig() {
        BeanConfig bankServiceProxyConfig = new BeanConfig("bankService", "com.tw.factories.ProxyFactoryBean");
        bankServiceProxyConfig.addProperty(new BeanProperty("target", "bankServiceTarget"));
        bankServiceProxyConfig.addProperty(new BeanProperty("interfaces", of("com.tw.service.BankService")));
        bankServiceProxyConfig.addProperty(new BeanProperty("interceptorNames", of("validateInterceptor", "bonusInterceptor")));
        return bankServiceProxyConfig;
    }

    private BeanConfig createValidateInterceptor(){
        return new BeanConfig("validateInterceptor", "com.tw.interceptors.QueryValidateInterceptor");
    }

    private BeanConfig createBonusInterceptor(){
        return new BeanConfig("bonusInterceptor", "com.tw.interceptors.BonusInterceptor");
    }
}
