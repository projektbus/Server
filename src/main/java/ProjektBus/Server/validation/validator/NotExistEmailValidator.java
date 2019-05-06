package ProjektBus.Server.validation.validator;

import ProjektBus.Server.service.UserService;
import ProjektBus.Server.validation.validationInterfaces.NotExistEmail;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.util.Objects.isNull;

public class NotExistEmailValidator implements ConstraintValidator<NotExistEmail, String> {

    @Autowired
    private UserService userService;

    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email == null || isNull(userService.getUserByEmail(email));
    }

    public void initialize(NotExistEmail constraintAnnotation) {
    }
}
