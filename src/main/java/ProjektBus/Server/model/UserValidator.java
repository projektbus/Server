package ProjektBus.Server.model;

import ProjektBus.Server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if(userService.getUserByLogin(user.getLogin())!=null) {
            errors.reject("login","Login already exists");
        }

        if(userService.getUserByEmail(user.getEmail())!=null) {
            errors.reject("email","Email already exists");
        }

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        if(!Pattern.compile(emailRegex).matcher(user.getEmail()).matches()) {
            errors.reject("email","Wrong email address");
        }

        String passwordRegexLetters = ".*[a-zA-Z+].*";
        if(!Pattern.compile(passwordRegexLetters).matcher(user.getPassword()).matches()) {
            errors.reject("password","Password must contain letters");

        }

        String passwordRegexNumbers =".*\\d+.*";
        if(!Pattern.compile(passwordRegexNumbers).matcher(user.getPassword()).matches()) {
            errors.reject("password","Password must contain numbers");
        }

        String passwordRegexSpecial = ".*[^A-Za-z0-9].*";
        if(!Pattern.compile(passwordRegexSpecial).matcher(user.getPassword()).matches()) {
            errors.reject("password","Password must contain special characters");
        }
    }
}
