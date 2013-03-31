package com.tw;

import com.tw.config.ConfigsImpl;
import com.tw.context.ApplicationContext;
import com.tw.context.ApplicationContextImpl;
import com.tw.service.BankService;

/**
 * User: Haiyang
 * Date: 3/6/13
 * Time: 10:50 PM
 */
public class Bank {
    public static void main(String[] args) {
        ApplicationContext context = new ApplicationContextImpl(new ConfigsImpl());
        BankService service = context.getBean("bankService");
        int money = service.query("aa");
        System.out.println(money);
    }
}
