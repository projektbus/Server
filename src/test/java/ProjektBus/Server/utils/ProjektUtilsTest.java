package ProjektBus.Server.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjektUtilsTest {

    @Test
    public void encodeAndVerifyPassword() {
        String password = "testPassword1";
        String passwordEncoded = ProjektUtils.passwordEncode(password);
        assertTrue(ProjektUtils.passwordVerify(passwordEncoded, password));
    }
}
