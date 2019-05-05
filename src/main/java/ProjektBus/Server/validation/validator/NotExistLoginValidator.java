package ProjektBus.Server.validation.validator;

import ProjektBus.Server.repository.UserRepository;
import ProjektBus.Server.validation.validationInterfaces.NotExistLogin;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.util.Objects.isNull;

public class NotExistLoginValidator implements ConstraintValidator<NotExistLogin, String> {

    @Autowired
    private UserRepository userRepository;

    public boolean isValid(String login, ConstraintValidatorContext context) {
        return login == null || isNull(userRepository.findByLogin(login));
    }

    public void initialize(NotExistLogin constraint) {
    }
}
