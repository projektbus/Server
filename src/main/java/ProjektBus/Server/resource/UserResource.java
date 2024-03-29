package ProjektBus.Server.resource;

import ProjektBus.Server.model.ConfirmationToken;
import ProjektBus.Server.model.SettingPasswordToken;
import ProjektBus.Server.model.User;
import ProjektBus.Server.model.template.ChangePasswordTemplate;
import ProjektBus.Server.model.template.LoginTemplate;
import ProjektBus.Server.model.template.PasswordTemplate;
import ProjektBus.Server.service.interfaces.ConfirmationTokenService;
import ProjektBus.Server.service.interfaces.EmailSenderService;
import ProjektBus.Server.service.interfaces.SettingPasswordTokenService;
import ProjektBus.Server.service.interfaces.UserService;
import ProjektBus.Server.utils.ApplicationError;
import ProjektBus.Server.utils.ApplicationResponse;
import ProjektBus.Server.utils.ErrorCodes;
import ProjektBus.Server.utils.ProjektUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

@RestController
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private SettingPasswordTokenService settingPasswordTokenService;

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @PostMapping("/register")
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
            if (user.isEnabled()) {
                return new ResponseEntity<>(new ApplicationError(ErrorCodes.ACCOUNT_ALREADY_EXIST), HttpStatus.CONFLICT);
            } else {
                user.setEnabled(true);
                userService.registerUser(user);
                return new ResponseEntity(HttpStatus.OK);
            }
        } else
            return new ResponseEntity<>(new ApplicationError(ErrorCodes.TOKEN_NOT_FOUND), HttpStatus.NOT_FOUND);

    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/users/{login}")
    public @ResponseBody
    ResponseEntity getUser(@PathVariable String login) {
        if (null != userService.getUserByLogin(login)) {
            return new ResponseEntity<>(userService.getUserByLogin(login), HttpStatus.OK);
        } else if (null != userService.getUserByEmail(login)) {
            return new ResponseEntity<>(userService.getUserByEmail(login), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApplicationError(ErrorCodes.USER_NOT_FOUND), HttpStatus.NOT_FOUND);
        }

    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/users")
    public @ResponseBody
    ResponseEntity getUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @PostMapping("/login")
    public ResponseEntity postLogin(@RequestBody LoginTemplate loginTemplate) {
        User user = userService.getUserByLogin(loginTemplate.getLogin());

        if (user != null) {
            if (ProjektUtils.passwordVerify(user.getPassword(),loginTemplate.getPassword())) {
                return createToken(user);
            } else
                return new ResponseEntity<>(new ApplicationError(ErrorCodes.WRONG_PASSWORD), HttpStatus.NOT_FOUND);
        }

        user = userService.getUserByEmail(loginTemplate.getLogin());
        if (user != null) {
            if (ProjektUtils.passwordVerify(user.getPassword(),loginTemplate.getPassword())) {
                return createToken(user);
            } else
                return new ResponseEntity<>(new ApplicationError( ErrorCodes.WRONG_PASSWORD), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ApplicationError(ErrorCodes.WRONG_LOGIN), HttpStatus.NOT_FOUND);
    }

    private ResponseEntity createToken(User user) {
        long currentTimeMillis = System.currentTimeMillis();
        String token = Jwts.builder()
                .claim("login", user.getLogin())
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + 7200000))
                .signWith(SignatureAlgorithm.HS512, "TbUL55^O|T<;UyT".getBytes())
                .compact();

        return new ResponseEntity<>(new ApplicationResponse(token), HttpStatus.OK);
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
    @PostMapping("/remind-password/{login}")
    public ResponseEntity remindPassword(@PathVariable("login") String login) {

        User user = userService.getUserByLogin(login) != null ? userService.getUserByLogin(login) :
                userService.getUserByEmail(login);

        if (user != null) {
            SettingPasswordToken resetToken = new SettingPasswordToken(user.getId());
            settingPasswordTokenService.save(resetToken);
            sendEmailWithRemindPasswordToken(user, resetToken);
            return new ResponseEntity<>(new ApplicationResponse(ErrorCodes.MAIL_SEND), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApplicationError(ErrorCodes.USER_NOT_FOUND), HttpStatus.NOT_FOUND);
        }
    }

    private void sendEmailWithRemindPasswordToken(User user, SettingPasswordToken resetToken) {
        SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
        passwordResetEmail.setFrom("support@demo.com");
        passwordResetEmail.setTo(user.getEmail());
        passwordResetEmail.setSubject("Remind password");
        passwordResetEmail.setText("To set new password, click the link below:\n"
                + "https://project-bus-web.herokuapp.com/change/" + resetToken.getTokenCode());

        emailSenderService.sendEmail(passwordResetEmail);
    }


    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @PostMapping("/remind-password")
    public ResponseEntity remindPassword(@RequestParam("tokenCode") String resetToken, @Valid @RequestBody PasswordTemplate passwordTemplate) {
        SettingPasswordToken token = settingPasswordTokenService.getByTokenCode(resetToken);

        if (token.isActive()) {
            User user = userService.getUserById(token.getUserId());
            String passwordEncode = ProjektUtils.passwordEncode(passwordTemplate.getPassword());
            user.setPassword(passwordEncode);
            userService.registerUser(user);
            token.setActive(false);
            settingPasswordTokenService.save(token);
            return new ResponseEntity<>(new ApplicationResponse(ErrorCodes.PASSWORD_CHANGE_SUCCESSFUL), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApplicationError(ErrorCodes.PASSWORD_ALREADY_CHANGED), HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @PutMapping("/users")
    public @ResponseBody
    ResponseEntity changePassword(@Valid @RequestBody ChangePasswordTemplate changePasswordTemplate) {
        User user = userService.getUserByLogin(changePasswordTemplate.getLogin());
        if (null != user) {
            if (ProjektUtils.passwordVerify(user.getPassword(), changePasswordTemplate.getPassword())) {
                if(ProjektUtils.passwordVerify(user.getPassword(), changePasswordTemplate.getNewPassword())){
                    return new ResponseEntity<>(new ApplicationError(ErrorCodes.PASSWORD_MUST_BE_DIFFERENT), HttpStatus.BAD_REQUEST);
                }
                String passwordEncode = ProjektUtils.passwordEncode(String.valueOf(changePasswordTemplate.getNewPassword()));
                user.setPassword(passwordEncode);
                userService.registerUser(user);
                return new ResponseEntity(HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApplicationError(ErrorCodes.WRONG_PASSWORD), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApplicationError(ErrorCodes.USER_NOT_FOUND), HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @DeleteMapping("/users/{login}")
    public ResponseEntity deleteUser(@PathVariable("login") String login) {
        User user = userService.getUserByLogin(login);
        if (null != user) {
            userService.deleteUser(user);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApplicationError(ErrorCodes.USER_NOT_FOUND), HttpStatus.NOT_FOUND);
        }
    }
}
