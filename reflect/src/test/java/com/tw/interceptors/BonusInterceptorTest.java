package com.tw.interceptors;

import com.tw.dao.BankDao;
import com.tw.service.DefaultBankService;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * User: Haiyang
 * Date: 3/31/13
 * Time: 11:33 PM
 */
public class BonusInterceptorTest {
    private Class<DefaultBankService> clazz;
    private Method depositMethod;
    private BonusInterceptor interceptor;

    @Before
    public void setUp() throws Exception {
        clazz = DefaultBankService.class;
        depositMethod = getDepositMethod();
        interceptor = new BonusInterceptor();
    }

    @Test
    public void shouldReturnTheOldParametersIfTheMethodNotDeposit() throws NoSuchMethodException {
        Method setBankDaoMethod = clazz.getDeclaredMethod("setBankDao", BankDao.class);
        Object[] parameters = interceptor.before(setBankDaoMethod, null);
        assertThat(parameters, nullValue());
    }

    @Test
    public void shouldReturnTheOldParametersIfTheMethodIsDepositAndTheMoneyLessThan100() {
        Object[] parameters = interceptor.before(depositMethod, new Object[]{"haha", 10.0});
        assertThat((Double)parameters[1], is(10.0));
    }

    @Test
    public void shouldReturnTheMore5BonusIfTheMethodIsDepositAndTheMoneyMoreThan100() {
        Object[] parameters = interceptor.before(depositMethod, new Object[]{"haha",101.0});
        assertThat((Double)parameters[1], is(106.0));
    }

    private Method getDepositMethod(){
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.getName().contains("deposit")) {
                return declaredMethod;
            }
        }
        return null;
    }
}
