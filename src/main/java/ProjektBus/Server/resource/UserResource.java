package ProjektBus.Server.resource;

import ProjektBus.Server.model.ConfirmationToken;
import ProjektBus.Server.model.User;
import ProjektBus.Server.validation.UserValidator;
import ProjektBus.Server.service.ConfirmationTokenService;
import ProjektBus.Server.service.EmailSenderService;
import ProjektBus.Server.service.UserService;
import ProjektBus.Server.utils.ProjektUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @PostMapping("/user")
    public ResponseEntity saveUser(@Valid @RequestBody User user) throws URISyntaxException {
        String passwordEncode = ProjektUtils.passwordEncode(user.getPassword());
        user.setPassword(passwordEncode);
        userService.registerUser(user);

        ConfirmationToken confirmationToken = new ConfirmationToken(user.getId());
        confirmationTokenService.save(confirmationToken);
        sendEmailWithConfirmationTokenToUser(user, confirmationToken);

        return ResponseEntity.created(new URI("https://peaceful-sierra-14544.herokuapp.com/user?login=" + user.getLogin())).build();
    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @PostMapping("/confirm-account")
    public ResponseEntity confirmAccount(@RequestParam("tokenCode") String confirmationToken) {
        ConfirmationToken token = confirmationTokenService.getByTokenCode(confirmationToken);

        if(token != null) {
            User user = userService.getUserById(token.getUserId());
            if(user.isEnabled()) {
                return new ResponseEntity("ACCOUNT ALREADY CONFIRMED", HttpStatus.CONFLICT);
            }
            else {
                user.setEnabled(true);
                userService.registerUser(user);
                return new ResponseEntity(HttpStatus.OK);
            }
        }
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);

    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/user")
    public @ResponseBody ResponseEntity getUser(@RequestParam("login") String login)  {
        if (null != userService.getUserByLogin(login)) {
            return new ResponseEntity(userService.getUserByLogin(login), HttpStatus.OK);
        }
        else if (null != userService.getUserByEmail(login)) {
            return new ResponseEntity(userService.getUserByEmail(login), HttpStatus.OK);
        }
        else {
            return new ResponseEntity("USER DOES NOT EXIST", HttpStatus.NOT_FOUND);
        }

    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/users")
    public @ResponseBody ResponseEntity getUsers() {
        return new ResponseEntity(userService.getAllUsers(), HttpStatus.OK);

    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @PostMapping("/login")
    public @ResponseBody ResponseEntity getLogin(@RequestParam("login") String login,@RequestParam("password") String password) {

        if (null != userService.getUserByLogin(login)) {
            User userLogin = userService.getUserByLogin(login);
            User userMail = userService.getUserByEmail(login);
            String passwordEncode = ProjektUtils.passwordEncode(password);

            if (userLogin != null) {
                if (userLogin.getPassword().equals(passwordEncode)) {
                    return new ResponseEntity("USER LOGGED SUCCESSFULLY", HttpStatus.OK);
                } else
                    return new ResponseEntity("WRONG DATA", HttpStatus.NOT_FOUND);
            } else if (userMail != null) {
                if (userMail.getPassword().equals(passwordEncode)) {
                    return new ResponseEntity("USER LOGGED SUCCESSFULLY", HttpStatus.OK);
                } else
                    return new ResponseEntity("WRONG DATA", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity("WRONG DATA", HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity("WRONG DATA", HttpStatus.NOT_FOUND);
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

    @InitBinder("user")
    public void setupBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }
}
