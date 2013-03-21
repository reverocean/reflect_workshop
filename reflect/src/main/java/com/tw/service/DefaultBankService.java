package com.tw.service;

import com.tw.dao.BankDao;

/**
 * User: Haiyang
 * Date: 3/6/13
 * Time: 10:45 PM
 */
public class DefaultBankService implements BankService {
    private BankDao bankDao;

    @Override
    public void deposit(String account, double money) {
        bankDao.deposit(account, money);
    }

    @Override
    public int query(String account) {
        return bankDao.query(account);
    }

    public void setBankDao(BankDao bankDao) {
        this.bankDao = bankDao;
    }
}
