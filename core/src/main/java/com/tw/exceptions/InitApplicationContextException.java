package com.tw.exceptions;

/**
 * User: Haiyang
 * Date: 3/31/13
 * Time: 2:14 PM
 */
public class InitApplicationContextException extends RuntimeException {
    public InitApplicationContextException(String message, Throwable e) {
        super(message, e);
    }
}
