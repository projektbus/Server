package ProjektBus.Server.model;

import ProjektBus.Server.repository.UserRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.regex.Pattern;

@Validated
@Data
@NoArgsConstructor
public class User implements Validator {
    @Autowired
    private UserRepository userRepository;
    @Id
    private String id;
    @Size(min=5,max=32)
    @NotNull
    private String login;
    @Size(min=5,max=32)
    @NotNull
    private String email;
    @Size(min=5,max=32)
    @NotNull
    private String password;
    @Autowired
    public User( String login, String email, String password) {

        this.login = login;
        this.email = email;
        this.password = password;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        /*if(userRepository.findByLogin(user.getLogin())!=null) {
            errors.reject(String.valueOf(1),"User already exists");
        }

        if(userRepository.findByEmail(user.getEmail())!=null) {
            errors.reject(String.valueOf(2),"Email already exists");
        }*/

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);

        if(!pat.matcher(user.getEmail()).matches()) {
            errors.reject("3","Wrong email address");
        }

        String passwordRegexLetters = ".*[a-zA-Z+].*";
        pat=Pattern.compile(passwordRegexLetters);
        if(!pat.matcher(user.getPassword()).matches()) {
            errors.reject("4","Password must contain letters");

        }

        String passwordRegexNumbers =".*\\d+.*";
        pat=Pattern.compile(passwordRegexNumbers);
        if(!pat.matcher(user.getPassword()).matches()) {
            errors.reject("5","Password must contain numbers");
        }

        String passwordRegexSpecial = ".*[^A-Za-z0-9].*";
        pat=Pattern.compile(passwordRegexSpecial);
        if(!pat.matcher(user.getPassword()).matches()) {
            errors.reject("6","Password must contain special characters");
        }
    }

}

