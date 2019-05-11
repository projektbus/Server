package ProjektBus.Server.service;

import ProjektBus.Server.model.SettingPasswordToken;

public interface SettingPasswordTokenService {
    SettingPasswordToken save(SettingPasswordToken settingPasswordToken);
    SettingPasswordToken getByTokenCode(String code);
}
