package com.tw.context;

import com.tw.config.BeanConfig;
import com.tw.config.BeanProperty;
import com.tw.config.Configs;
import com.tw.exceptions.BeanNotFoundException;
import com.tw.exceptions.InitApplicationContextException;
import com.tw.factories.FactoryBean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * User: Haiyang
 * Date: 3/18/13
 * Time: 6:51 PM
 */
public class ApplicationContextImpl implements ApplicationContext {
    private Configs configs;
    private ConcurrentMap<String, Object> beans = new ConcurrentHashMap<String, Object>();
    private ConcurrentMap<String, Class> clazzs = new ConcurrentHashMap<String, Class>();

    public ApplicationContextImpl(Configs configs) {
        checkNotNull(configs);
        this.configs = configs;
        initBeans();
        initProperties();
    }


    @Override
    public <T> T getBean(String beanName) {
        if (!beans.containsKey(beanName)) {
            throw new BeanNotFoundException(String.format("%s not found in the Application Context", beanName));
        }
        return (T) beans.get(beanName);
    }

    private void initProperties() {
        for (BeanConfig beanConfig : configs.getBeanConfigs()) {
            String className = beanConfig.getClassName();
            String beanName = beanConfig.getName();
            Class clazz = clazzs.get(className);
            Method[] declaredMethods = clazz.getDeclaredMethods();
            for (BeanProperty beanProperty : beanConfig.getProperties()) {

                for (Method method : declaredMethods) {
                    String methodName = method.getName();
                    if (methodName.startsWith("set") && methodName.substring(3).equalsIgnoreCase(beanProperty.getName())) {
                        Object bean = beans.get(beanName);
                        try {
                            if(beanProperty.getRef() != null){
                                method.invoke(bean, new Object[]{beans.get(beanProperty.getRef())});
                            }
                            if (beanProperty.getList() != null) {
                                method.invoke(bean, new Object[]{beanProperty.getList()});
                            }
                        } catch (IllegalAccessException e) {
                            throw new InitApplicationContextException(String.format("Can't initialize bean: %s", beanName), e);
                        } catch (InvocationTargetException e) {
                            throw new InitApplicationContextException(String.format("Can't initialize bean: %s", beanName), e);
                        } catch (Throwable e) {
                            throw new InitApplicationContextException(String.format("Can't initialize bean: %s", beanName), e);
                        }
                    }
                }
            }
        }
    }

    private void initBeans() {
        for (BeanConfig beanConfig : configs.getBeanConfigs()) {
            beans.put(beanConfig.getName(), createBean(beanConfig.getClassName()));
        }
    }

    private Object createBean(String className) {
        try {
            Class clazz = getClazz(className);
            clazzs.put(className, clazz);
            Object bean = clazz.newInstance();
            initApplicationContextAware(bean);
            if (isFactoryBean(bean)) {
                return ((FactoryBean)bean).getObject();
            }
            return bean;
        } catch (InstantiationException e) {
            throw new InitApplicationContextException(String.format("Can't initialize bean: %s", className), e);
        } catch (IllegalAccessException e) {
            throw new InitApplicationContextException(String.format("Can't initialize bean: %s", className), e);
        }
    }

    private boolean isFactoryBean(Object bean) {
        return bean instanceof FactoryBean;
    }

    private void initApplicationContextAware(Object bean) {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware)bean).setApplicationContext(this);
        }
    }

    private Class getClazz(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new InitApplicationContextException(String.format("%s not found in the classpath", className), e);
        }
    }
}
