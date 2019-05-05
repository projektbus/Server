package ProjektBus.Server.validation.validator;

import ProjektBus.Server.repository.BusStopRepository;
import ProjektBus.Server.validation.validationInterfaces.UniqueBusStopName;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.util.Objects.isNull;

public class UniqueBusStopNameValidator implements ConstraintValidator<UniqueBusStopName, String> {

    @Autowired
    private BusStopRepository busStopRepository;

    public boolean isValid(String name, ConstraintValidatorContext context) {
        return name == null || isNull(busStopRepository.findByName(name));
    }

    public void initialize(UniqueBusStopName constraint) {
    }

}
