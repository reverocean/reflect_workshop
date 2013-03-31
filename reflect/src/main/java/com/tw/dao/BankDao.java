package com.tw.dao;

/**
 * User: Haiyang
 * Date: 3/17/13
 * Time: 12:13 AM
 */
public interface BankDao {
    void deposit(String account, double money);
    double query(String account);
}
