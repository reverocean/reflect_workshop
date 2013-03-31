package com.tw.dao;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: Haiyang
 * Date: 3/31/13
 * Time: 10:59 PM
 */
public class DefaultBankDaoTest {
    @Test
    public void shouldReturn0IfWithOutDeposit(){
        DefaultBankDao dao = new DefaultBankDao();
        assertThat(dao.query("test"), is(0.0));
    }

    @Test
    public void shouldReturnDepositSumIfDeposit(){
        DefaultBankDao dao = new DefaultBankDao();
        String account = "test";
        dao.deposit(account, 10);
        assertThat(dao.query(account), is(10.0));
        dao.deposit(account, 10);
        assertThat(dao.query(account), is(20.0));
    }
}
