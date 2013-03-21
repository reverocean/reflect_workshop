package com.tw.config;

/**
 * User: Haiyang
 * Date: 3/20/13
 * Time: 12:28 PM
 */
public class BeanProperty {
    private String name;
    private String ref;
    private String value;

    public BeanProperty(String name, String ref) {
        this.name = name;
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
