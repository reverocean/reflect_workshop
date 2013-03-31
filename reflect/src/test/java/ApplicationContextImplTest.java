import com.tw.config.ConfigsImpl;
import com.tw.context.ApplicationContext;
import com.tw.context.ApplicationContextImpl;
import com.tw.service.BankService;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * User: Haiyang
 * Date: 3/31/13
 * Time: 1:48 PM
 */
public class ApplicationContextImplTest {

    private ApplicationContext applicationContext;
    private BankService bankService;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ApplicationContextImpl(new ConfigsImpl());
        bankService = applicationContext.getBean("bankService");
    }

    @Test
    public void shouldReturn10IfAccountDepositLessThan10() {
        String account = "account";
        bankService.deposit(account, 10.0);
        assertThat(bankService.query(account), is(10.0));
    }

    @Test
    public void shouldReturn110IfAccountDeposit105() {
        String account = "account";
        bankService.deposit(account, 105.0);
        assertThat(bankService.query(account), is(110.0));
    }

    @Test
    public void shouldReturnMinus1IfAccountIsLaden() {
        String account = "Laden";
        bankService.deposit(account, 1000000000.0);
        assertThat(bankService.query(account), is(-1.0));
    }
}
