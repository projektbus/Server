package ProjektBus.Server.service;
import ProjektBus.Server.model.SettingPasswordToken;
import ProjektBus.Server.repository.SettingPasswordTokenRepository;
import ProjektBus.Server.service.interfaces.SettingPasswordTokenService;
import org.springframework.beans.factory.annotation.Autowired;

public class SettingPasswordTokenServiceImpl implements SettingPasswordTokenService {

    @Autowired
    private SettingPasswordTokenRepository settingPasswordTokenRepository;

    @Override
    public SettingPasswordToken save(SettingPasswordToken settingPasswordToken) {
        return settingPasswordTokenRepository.save(settingPasswordToken);
    }

    @Override
    public SettingPasswordToken getByTokenCode(String code) {
        return settingPasswordTokenRepository.findByTokenCode(code);
    }
}
