package ProjektBus.Server.resource;

import ProjektBus.Server.model.ConfirmationToken;
import ProjektBus.Server.model.ResetToken;
import ProjektBus.Server.model.User;
import ProjektBus.Server.model.template.ForgotPasswordTemplate;
import ProjektBus.Server.model.template.LoginTemplate;
import ProjektBus.Server.model.template.RemindPasswordTemplate;
import ProjektBus.Server.service.ConfirmationTokenService;
import ProjektBus.Server.service.EmailSenderService;
import ProjektBus.Server.service.ResetTokenService;
import ProjektBus.Server.service.UserService;
import ProjektBus.Server.utils.ProjektUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class UserResource {

    @Autowired
    private ResetTokenService resetTokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @PostMapping("/users")
    public ResponseEntity saveUser(@Valid @RequestBody User user) throws URISyntaxException {
        String passwordEncode = ProjektUtils.passwordEncode(user.getPassword());
        user.setPassword(passwordEncode);
        userService.registerUser(user);

        ConfirmationToken confirmationToken = new ConfirmationToken(user.getId());
        confirmationTokenService.save(confirmationToken);
            sendEmailWithConfirmationTokenToUser(user, confirmationToken);

        return ResponseEntity.created(new URI("https://peaceful-sierra-14544.herokuapp.com/users/" + user.getLogin())).build();
    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @PostMapping("/confirm-account")
    public ResponseEntity confirmAccount(@RequestParam("tokenCode") String confirmationToken) {
        ConfirmationToken token = confirmationTokenService.getByTokenCode(confirmationToken);

        if (token != null) {
            User user = userService.getUserById(token.getUserId());
            if(user.isEnabled()) {
                return new ResponseEntity("Account already confirmed", HttpStatus.CONFLICT);
            }
            else {
                user.setEnabled(true);
                userService.registerUser(user);
                return new ResponseEntity(HttpStatus.OK);
            }
        } else
            return new ResponseEntity(HttpStatus.NOT_FOUND);

    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/users/{login}")
    public @ResponseBody ResponseEntity getUser(@PathVariable String login)  {
        if (null != userService.getUserByLogin(login)) {
            return new ResponseEntity(userService.getUserByLogin(login), HttpStatus.OK);
        } else if (null != userService.getUserByEmail(login)) {
            return new ResponseEntity(userService.getUserByEmail(login), HttpStatus.OK);
        }
        else {
            return new ResponseEntity("User does not exist", HttpStatus.NOT_FOUND);
        }

    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/users")
    public @ResponseBody
    ResponseEntity getUsers() {
        return new ResponseEntity(userService.getAllUsers(), HttpStatus.OK);

    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @PostMapping("/login")
    public ResponseEntity postLogin(@RequestBody LoginTemplate loginTemplate) {

        if (null != userService.getUserByLogin(loginTemplate.getLogin()) || null != userService.getUserByEmail(loginTemplate.getLogin())) {
            User userByLogin = userService.getUserByLogin(loginTemplate.getLogin());
            User userByMail = userService.getUserByEmail(loginTemplate.getLogin());
            if (userByLogin != null) {
                if (ProjektUtils.passwordVerify(userByLogin.getPassword(),loginTemplate.getPassword())) {
                    return new ResponseEntity("User logged successfully", HttpStatus.OK);
                } else
                    return new ResponseEntity("Wrong password", HttpStatus.NOT_FOUND);
            } else if (userByMail != null) {
                if (ProjektUtils.passwordVerify(userByMail.getPassword(),loginTemplate.getPassword())) {
                    return new ResponseEntity("User logged successfully", HttpStatus.OK);
                } else
                    return new ResponseEntity("Wrong password", HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity("Wrong login", HttpStatus.NOT_FOUND);
    }


    private void sendEmailWithConfirmationTokenToUser(User user, ConfirmationToken confirmationToken) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration to ProjectBus!");
        mailMessage.setFrom("projektbus2019@gmail.com");
        mailMessage.setText("To confirm your account, please click here : \n"
                + "https://peaceful-sierra-14544.herokuapp.com/confirm-account?tokenCode=" + confirmationToken.getTokenCode());

        emailSenderService.sendEmail(mailMessage);
    }
    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @PostMapping("/forgot-password")
    public ResponseEntity forgotPassword(@RequestBody ForgotPasswordTemplate forgotPasswordTemplate) {

        User user = userService.getUserByEmail(forgotPasswordTemplate.getEmail());

        if (user!=null) {
            ResetToken resetToken = new ResetToken(user.getId());
            resetTokenService.save(resetToken);
            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setFrom("support@demo.com");
            passwordResetEmail.setTo(user.getEmail());
            passwordResetEmail.setSubject("Password Remind Request");
            passwordResetEmail.setText("To remind your password, click the link below:\n"
                    + "https://peaceful-sierra-14544.herokuapp.com/remind-passsword?tokenCode=" + resetToken.getTokenCode());

            emailSenderService.sendEmail(passwordResetEmail);
        }
        return new ResponseEntity("Mail was sended",HttpStatus.OK);
    }
    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @PostMapping("/remind-password")
    public ResponseEntity remindPassword(@RequestParam("tokenCode") String resetToken, @RequestBody RemindPasswordTemplate remindPasswordTemplate) {
        ResetToken token = resetTokenService.getByTokenCode(resetToken);
        if(token.isUsed()) {
            return new ResponseEntity("Password already changed with this link", HttpStatus.CONFLICT);
        }
        else {
            if (remindPasswordTemplate.getPassword().equals(remindPasswordTemplate.getConfirmationPassword())) {
                User user = userService.getUserById(token.getUserId());
                String passwordEncode = ProjektUtils.passwordEncode(remindPasswordTemplate.getPassword());
                user.setPassword(passwordEncode);
                userService.registerUser(user);
                token.setUsed(true);
                resetTokenService.save(token);
                return new ResponseEntity("Password changed successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity("Password and confirmation password do not match.", HttpStatus.NOT_FOUND);
            }
        }
    }
}


