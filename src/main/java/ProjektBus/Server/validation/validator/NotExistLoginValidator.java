package ProjektBus.Server.validation.validator;

import ProjektBus.Server.service.UserService;
import ProjektBus.Server.validation.validationInterfaces.NotExistLogin;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.util.Objects.isNull;

public class NotExistLoginValidator implements ConstraintValidator<NotExistLogin, String> {

    @Autowired
    private UserService userService;

    public boolean isValid(String login, ConstraintValidatorContext context) {
        return login == null || isNull(userService.getUserByLogin(login));
    }

    public void initialize(NotExistLogin constraint) {
    }
}
