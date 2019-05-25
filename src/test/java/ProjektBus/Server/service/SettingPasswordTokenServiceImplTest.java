package ProjektBus.Server.service;

import ProjektBus.Server.model.SettingPasswordToken;
import ProjektBus.Server.service.interfaces.SettingPasswordTokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SettingPasswordTokenServiceImplTest {

    @Autowired
    private SettingPasswordTokenService settingPasswordTokenService;

    @Test
    public void shouldSaveResetToken() {
        SettingPasswordToken resetToken = new SettingPasswordToken("userId2");
        SettingPasswordToken token = settingPasswordTokenService.save(resetToken);
        assertNotNull(token);
        assertNotNull(token.getTokenId());
    }

    @Test
    public void shouldFindConfirmationToken() {
        SettingPasswordToken resetToken = new SettingPasswordToken("userId3");
        SettingPasswordToken token = settingPasswordTokenService.save(resetToken);
        assertNotNull(settingPasswordTokenService.getByTokenCode(token.getTokenCode()));
        assertEquals(settingPasswordTokenService.getByTokenCode(token.getTokenCode()).getUserId(), "userId3");
    }

}
