package ProjektBus.Server.resource;

import ProjektBus.Server.service.ResetTokenService;
import ProjektBus.Server.model.User;
import ProjektBus.Server.model.ResetToken;

import ProjektBus.Server.service.UserService;
import ProjektBus.Server.utils.ProjektUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestParam;


public class Remind {
    @Autowired
    private UserService userService;
    @Autowired
    private ResetTokenService resetTokenService;

    public void ForgotPassword(String userEmail) {

        User user = userService.getUserByEmail(userEmail);

        if (user!=null) {
            ResetToken resetToken = new ResetToken(user.getId());
            resetTokenService.save(resetToken);
            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setFrom("support@demo.com");
            passwordResetEmail.setTo(user.getEmail());
            passwordResetEmail.setSubject("Password Reset Request");
            passwordResetEmail.setText("To reset your password, click the link below:\n"
                    + "https://peaceful-sierra-14544.herokuapp.com/remind-passsword?tokenCode=" + resetToken.getTokenCode());

            emailService.sendEmail(passwordResetEmail);
        }
    }

    public void setNewPassword(@RequestParam("tokenCode") String resettoken, String newpassword) {
        ResetToken token = resetTokenService.getByTokenCode(resettoken);
        User user = userService.getUserById(token.getUserId());
        String passwordEncode = ProjektUtils.passwordEncode(newpassword);
        user.setPassword(passwordEncode);

    }
}
