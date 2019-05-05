package ProjektBus.Server.service;

import ProjektBus.Server.model.ResetToken;

public interface ResetTokenService {
    ResetToken save(ResetToken confirmationToken);
    ResetToken getByTokenCode(String code);
}
