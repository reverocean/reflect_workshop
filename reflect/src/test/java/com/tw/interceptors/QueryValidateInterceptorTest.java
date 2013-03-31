package com.tw.interceptors;

import com.tw.dao.BankDao;
import com.tw.service.BankService;
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
 * Time: 10:52 PM
 */
public class QueryValidateInterceptorTest {
    private Class<DefaultBankService> clazz;
    private Method queryMethod;
    private QueryValidateInterceptor interceptor;

    @Before
    public void setUp() throws Exception {
        clazz = DefaultBankService.class;
        queryMethod = clazz.getDeclaredMethod("query", String.class);
        interceptor = new QueryValidateInterceptor();
    }

    @Test
    public void shouldReturnTheOldReturnValueIfTheMethodNotQuery() throws NoSuchMethodException {
        Method setBankDaoMethod = clazz.getDeclaredMethod("setBankDao", BankDao.class);
        Object afterReturnValue = interceptor.after(setBankDaoMethod, null, null);
        assertThat(afterReturnValue, nullValue());
    }

    @Test
    public void shouldReturnMinus1IfTheMethodIsQueryAndTheAccountInForbiddenList()  {
        Double afterReturnValue = (Double) interceptor.after(queryMethod, new Object[]{"Laden"}, 10000000000.0);
        assertThat(afterReturnValue, is(-1.0));
    }

    @Test
    public void shouldReturnTheOldReturnValueIfTheMethodIsQueryAndTheAccountNotInForbiddenList() {
        Double afterReturnValue = (Double) interceptor.after(queryMethod, new Object[]{"haha"}, 100.0);
        assertThat(afterReturnValue, is(100.0));
    }
}
