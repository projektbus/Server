package ProjektBus.Server.service;

import ProjektBus.Server.model.ConfirmationToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfirmationTokenServiceImplTest {

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Test
    public void shouldSaveConfirmationToken() {
        ConfirmationToken confirmationToken = new ConfirmationToken("userId2");
        ConfirmationToken token = confirmationTokenService.save(confirmationToken);
        assertNotNull(token);
        assertNotNull(token.getTokenId());
    }

    @Test
    public void shouldFindConfirmationToken() {
        ConfirmationToken confirmationToken = new ConfirmationToken("userId3");
        ConfirmationToken token = confirmationTokenService.save(confirmationToken);
        assertNotNull(confirmationTokenService.getByTokenCode(token.getTokenCode()));
        assertEquals(confirmationTokenService.getByTokenCode(token.getTokenCode()).getUserId(), "userId3");
    }

}
