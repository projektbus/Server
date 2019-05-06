package ProjektBus.Server.service;

import ProjektBus.Server.model.ResetToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResetTokenServiceImplTest {

    @Autowired
    private ResetTokenService resetTokenService;

    @Test
    public void shouldSaveResetToken() {
        ResetToken resetToken = new ResetToken("userId2");
        ResetToken token = resetTokenService.save(resetToken);
        assertNotNull(token);
        assertNotNull(token.getTokenId());
    }

    @Test
    public void shouldFindConfirmationToken() {
        ResetToken resetToken = new ResetToken("userId3");
        ResetToken token = resetTokenService.save(resetToken);
        assertNotNull(resetTokenService.getByTokenCode(token.getTokenCode()));
        assertEquals(resetTokenService.getByTokenCode(token.getTokenCode()).getUserId(), "userId3");
    }

}
