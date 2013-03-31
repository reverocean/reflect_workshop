import com.tw.service.DefaultBankService;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: Haiyang
 * Date: 3/17/13
 * Time: 12:06 AM
 */
public class InitClassTest {

    private String defaultBankServiceClassName;

    @Before
    public void setUp() throws Exception {
        defaultBankServiceClassName = "com.tw.service.DefaultBankService";
    }

    @Test
    public void shouldGetClassFromGetClassMethod() {
        DefaultBankService service = new DefaultBankService();
        Class<? extends DefaultBankService> serviceClass = service.getClass();
        assertThat(serviceClass.getName(), is(defaultBankServiceClassName));
    }

    @Test
    public void shouldGetClassFromStaticClass() {
        assertThat(DefaultBankService.class.getName(), is(defaultBankServiceClassName));
    }

    @Test
    public void shouldGetClassFromForNameMethod() throws ClassNotFoundException {
        assertThat(DefaultBankService.class.getName(), is(Class.forName(defaultBankServiceClassName).getName()));
    }

    @Test
    public void shouldGetClassFromLoadClass() throws ClassNotFoundException {
        assertThat(DefaultBankService.class.getName(), is(this.getClass().getClassLoader().loadClass(defaultBankServiceClassName).getName()));
    }


}
