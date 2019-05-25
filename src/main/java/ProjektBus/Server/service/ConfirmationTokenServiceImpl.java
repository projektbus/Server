package ProjektBus.Server.service;

import ProjektBus.Server.model.ConfirmationToken;
import ProjektBus.Server.repository.ConfirmationTokenRepository;
import ProjektBus.Server.service.interfaces.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;

public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public ConfirmationToken save(ConfirmationToken confirmationToken) {
        return confirmationTokenRepository.save(confirmationToken);
    }

    @Override
    public ConfirmationToken getByTokenCode(String code) {
        return confirmationTokenRepository.findByTokenCode(code);
    }
}
