package com.tw.exceptions;

/**
 * User: Haiyang
 * Date: 3/31/13
 * Time: 1:55 PM
 */
public class BeanNotFoundException extends RuntimeException{
    public BeanNotFoundException(String message) {
        super(message);
    }
}
