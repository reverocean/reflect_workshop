package com.tw.config;

import com.tw.pojo.Tiger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * User: Haiyang
 * Date: 3/21/13
 * Time: 12:14 PM
 */
public class Performance {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException {
        field(1000);
        field(10000);
        field(100000);
        method(1000);
        method(10000);
        method(100000);

    }

    public static void field(int loops) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Tiger tiger = new Tiger();
        Class<Tiger> clazz = Tiger.class;
        Field legsField = clazz.getDeclaredField("legs");

        long start = System.currentTimeMillis();
        for (int i = 0; i < loops; i++) {
            int legs = tiger.legs;
        }
        System.out.println("Used time for field: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < loops; i++) {
            int legs = legsField.getInt(tiger);
        }
        System.out.println("Used time for field reflect: " + (System.currentTimeMillis() - start));
    }

    private static void method(int loops) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Tiger tiger = new Tiger();
        Class<Tiger> clazz = Tiger.class;
        long start = System.currentTimeMillis();
        for (int i = 0; i < loops; i++) {
            boolean sex = tiger.isSex();
        }
        System.out.println("Used time for method: " + (System.currentTimeMillis() - start));

        Method sexMethod = clazz.getDeclaredMethod("isSex", new Class[0]);
        start = System.currentTimeMillis();
        for (int i = 0; i < loops; i++) {
            sexMethod.invoke(tiger, new Object[0]);
        }
        System.out.println("Used time for method reflect: " + (System.currentTimeMillis() - start));
    }
}
