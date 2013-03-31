package com.tw.context;

import com.tw.config.BeanConfig;
import com.tw.config.BeanProperty;
import com.tw.config.Configs;
import com.tw.exceptions.BeanNotFoundException;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * User: Haiyang
 * Date: 3/31/13
 * Time: 1:54 PM
 */
public class ApplicationContextImplTest {
    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointWhenConfigsIsNull() {
        ApplicationContext context = new ApplicationContextImpl(null);
    }

    @Test(expected = BeanNotFoundException.class)
    public void shouldThrowBeanNotFoundExceptionWhenNoBean() {
        ApplicationContext context = new ApplicationContextImpl(new Configs() {
            @Override
            public List<BeanConfig> getBeanConfigs() {
                return of();
            }
        });
        context.getBean("notExistName");
    }

    @Test
    public void shouldReturnBankWhenGetBank() {
        final String beanName = "bank";
        final String className = "com.tw.context.Bank";
        ApplicationContext context = new ApplicationContextImpl(new Configs() {
            @Override
            public List<BeanConfig> getBeanConfigs() {
                return of(new BeanConfig(beanName, className));
            }
        });
        Object bank = context.getBean(beanName);
        assertThat(bank.getClass().getName(), is(className));
    }

    @Test
    public void shouldReturnBankWithNamePropertyWhenGetBank() {
        final String beanName = "bank";
        final String className = "com.tw.context.Bank";
        ApplicationContext context = new ApplicationContextImpl(new Configs() {
            @Override
            public List<BeanConfig> getBeanConfigs() {
                BeanConfig bankConfig = new BeanConfig(beanName, className);
                bankConfig.addProperty(new BeanProperty("name", "name"));
                BeanConfig nameConfig = new BeanConfig("name", "com.tw.context.Name");
                return of(bankConfig, nameConfig);
            }
        });
        Bank bank = context.getBean(beanName);
        assertThat(bank.getName(), is("China Bank"));
    }
}

class Bank {
    private Name name;

    public String getName() {
        return name.getName();
    }

    public void setName(Name name) {
        this.name = name;
    }
}

class Name {
    private String name;

    public Name() {
        this.name = "China Bank";
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
