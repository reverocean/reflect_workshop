package com.tw.config;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Haiyang
 * Date: 3/18/13
 * Time: 5:32 PM
 */
public class BeanConfig {
    private String name;
    private String className;
    private Scope scope;
    private List<BeanProperty> properties = new ArrayList<BeanProperty>();

    public BeanConfig(String name, String className, Scope scope) {
        this.name = name;
        this.className = className;
        this.scope = scope;
    }

    public BeanConfig(String name, String className) {
        this(name, className, Scope.SINGLETON);
    }

    public void addProperty(BeanProperty beanProperty) {
        this.properties.add(beanProperty);
    }

    public List<BeanProperty> getProperties() {
        return properties;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    public Scope getScope() {
        return scope;
    }
}
