package ProjektBus.Server.validation.validator;

import ProjektBus.Server.repository.UserRepository;
import ProjektBus.Server.validation.validationInterfaces.NotExistEmail;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.util.Objects.isNull;

public class NotExistEmailValidator implements ConstraintValidator<NotExistEmail, String> {

    @Autowired
    private UserRepository userRepository;

    public boolean isValid(String email, ConstraintValidatorContext context) {
        System.out.println(email == null);
        System.out.println(isNull(userRepository.findByEmail(email)));
        return email == null || isNull(userRepository.findByEmail(email));
    }

    public void initialize(NotExistEmail constraintAnnotation) {
    }
}
