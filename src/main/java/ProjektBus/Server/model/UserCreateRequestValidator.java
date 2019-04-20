package ProjektBus.Server.model;

import ProjektBus.Server.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.util.regex.Pattern;

@Component
public class UserCreateRequestValidator implements Validator {

    private UserRepository userRepository;

    public UserCreateRequestValidator(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserCreateRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserCreateRequest userCreateRequest = (UserCreateRequest) target;
        if(userRepository.findByLogin(userCreateRequest.getLogin())!=null) {
            errors.reject(String.valueOf(1),"User already exists");
        }

        if(userRepository.findByEmail(userCreateRequest.getEmail())!=null) {
            errors.reject(String.valueOf(2),"Email already exists");
        }

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);

        if(!pat.matcher(userCreateRequest.getEmail()).matches()) {
            errors.reject("3","Wrong email address");
        }

        String passwordRegexLetters = ".*[a-zA-Z+].*";
        pat=Pattern.compile(passwordRegexLetters);
        if(!pat.matcher(userCreateRequest.getPassword()).matches()) {
            errors.reject("4","Password must contain letters");

        }

        String passwordRegexNumbers =".*\\d+.*";
        pat=Pattern.compile(passwordRegexNumbers);
        if(!pat.matcher(userCreateRequest.getPassword()).matches()) {
            errors.reject("5","Password must contain numbers");
        }

        String passwordRegexSpecial = ".*[^A-Za-z0-9].*";
        pat=Pattern.compile(passwordRegexSpecial);
        if(!pat.matcher(userCreateRequest.getPassword()).matches()) {
            errors.reject("6","Password must contain special characters");
        }
    }
}
