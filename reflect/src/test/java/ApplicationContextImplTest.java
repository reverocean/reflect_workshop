import com.tw.config.ConfigsImpl;
import com.tw.context.ApplicationContext;
import com.tw.context.ApplicationContextImpl;
import com.tw.service.BankService;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * User: Haiyang
 * Date: 3/31/13
 * Time: 1:48 PM
 */
public class ApplicationContextImplTest {
    @Test
    public void getBackServiceShouldReturnBankServiceImpl() {
        ApplicationContext context = new ApplicationContextImpl(new ConfigsImpl());
        BankService service = context.getBean("bankService");
        int money = service.query("aa");
        assertThat(money, is(10));
    }
}
