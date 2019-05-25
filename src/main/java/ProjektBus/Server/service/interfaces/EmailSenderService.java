package ProjektBus.Server.service.interfaces;

import org.springframework.mail.SimpleMailMessage;

public interface EmailSenderService {
    void sendEmail(SimpleMailMessage email);
}
