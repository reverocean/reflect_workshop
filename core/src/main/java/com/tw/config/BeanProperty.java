package com.tw.config;

import java.util.List;

/**
 * User: Haiyang
 * Date: 3/20/13
 * Time: 12:28 PM
 */
public class BeanProperty {
    private String name;
    private String ref;
    private List<String> list;

    public BeanProperty(String name, String ref) {
        this.name = name;
        this.ref = ref;
    }

    public BeanProperty(String name, List<String> list) {
        this.name = name;
        this.list = list;
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

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
