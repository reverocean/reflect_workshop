package com.tw.service;

/**
 * User: Haiyang
 * Date: 3/17/13
 * Time: 12:12 AM
 */
public interface BankService {
    void deposit(String account, double money);

    double query(String account);
}
