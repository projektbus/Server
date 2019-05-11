package ProjektBus.Server.resource;

import ProjektBus.Server.model.ConfirmationToken;
import ProjektBus.Server.model.User;
import ProjektBus.Server.service.ConfirmationTokenService;
import ProjektBus.Server.service.EmailSenderService;
import ProjektBus.Server.service.UserService;
import ProjektBus.Server.utils.ProjektUtils;
import ProjektBus.Server.validation.UserValidator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.WebDataBinder;
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
    private UserValidator userValidator;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

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

    @GetMapping("/users")
    public @ResponseBody ResponseEntity getUsers() {
        return new ResponseEntity(userService.getAllUsers(), HttpStatus.OK);

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

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody User user){
        long currentTimeMillis = System.currentTimeMillis();
        String token = Jwts.builder()
                .claim("login", user.getLogin())
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + 7200000))
                .signWith(SignatureAlgorithm.HS512, "TbUL55^O|T<;UyT".getBytes())
                .compact();

        return new ResponseEntity(token, HttpStatus.OK);
    }

    @InitBinder("user")
    public void setupBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }
}
