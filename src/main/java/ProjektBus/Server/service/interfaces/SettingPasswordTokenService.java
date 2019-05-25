package ProjektBus.Server.service.interfaces;

import ProjektBus.Server.model.SettingPasswordToken;

public interface SettingPasswordTokenService {
    SettingPasswordToken save(SettingPasswordToken settingPasswordToken);
    SettingPasswordToken getByTokenCode(String code);
}
