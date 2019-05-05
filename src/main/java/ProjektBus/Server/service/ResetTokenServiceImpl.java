package ProjektBus.Server.service;
import ProjektBus.Server.model.ResetToken;
import ProjektBus.Server.repository.ResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ResetTokenServiceImpl implements ResetTokenService {

    @Autowired
    private ResetTokenRepository resetTokenRepository;

    @Override
    public ResetToken save(ResetToken resetToken) {
        return resetTokenRepository.save(resetToken);
    }

    @Override
    public ResetToken getByTokenCode(String code) {
        return resetTokenRepository.findByTokenCode(code);
    }
}