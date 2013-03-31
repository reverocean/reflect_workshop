package com.tw.dao;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

/**
 * User: Haiyang
 * Date: 3/6/13
 * Time: 10:47 PM
 */
public class DefaultBankDao implements BankDao {
    private Map<String, Double> accounts = newHashMap();

    public void deposit(String account, double money) {
        if (isAccountExisted(account)) {
            money += accounts.get(account);
        }
        accounts.put(account, money);
    }

    public double query(String account) {
        if (isAccountExisted(account)) {
            return accounts.get(account);
        }
        return 0;
    }

    private boolean isAccountExisted(String account) {
        return accounts.containsKey(account);
    }
}
