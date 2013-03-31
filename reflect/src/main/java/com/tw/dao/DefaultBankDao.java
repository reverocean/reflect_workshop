package com.tw.dao;

/**
 * User: Haiyang
 * Date: 3/6/13
 * Time: 10:47 PM
 */
public class DefaultBankDao implements BankDao {
    public void deposit(String account, double money) {
        System.out.println(money);
    }

    public int query(String account) {
        return 10;
    }
}
