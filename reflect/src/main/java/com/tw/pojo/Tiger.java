package com.tw.pojo;

/**
 * User: Haiyang
 * Date: 3/6/13
 * Time: 5:46 PM
 */
public class Tiger extends Animal {
    public int legs;
    private boolean sex;

    public Tiger() {
    }

    public Tiger(int legs) {
        this(legs, true);
    }

    private Tiger(int legs, boolean sex) {
        super();
        this.legs = legs;
        this.sex = sex;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public static String thisStaticMethod() {
        return "abc";
    }
}
