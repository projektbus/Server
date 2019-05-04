package ProjektBus.Server.validation;

import ProjektBus.Server.repository.BusStopRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.util.Objects.isNull;

class UniqueBusStopNameValidator implements ConstraintValidator<UniqueBusStopName, String> {

    @Autowired
    private BusStopRepository busStopRepository;

    public void initialize(UniqueBusStopName constraint) {
    }

    public boolean isValid(String name, ConstraintValidatorContext context) {
        return name != null && isNull(busStopRepository.findByName(name));
    }

}
