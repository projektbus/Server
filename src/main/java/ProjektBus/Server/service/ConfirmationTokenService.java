package ProjektBus.Server.service;

import ProjektBus.Server.model.ConfirmationToken;

public interface ConfirmationTokenService {
    ConfirmationToken save(ConfirmationToken confirmationToken);
    ConfirmationToken getByTokenCode(String code);
}
