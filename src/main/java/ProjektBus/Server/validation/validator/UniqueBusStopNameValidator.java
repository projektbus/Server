package ProjektBus.Server.validation.validator;

import ProjektBus.Server.service.BusStopService;
import ProjektBus.Server.validation.validationInterfaces.UniqueBusStopName;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.util.Objects.isNull;

public class UniqueBusStopNameValidator implements ConstraintValidator<UniqueBusStopName, String> {

    @Autowired
    private BusStopService busStopService;

    public boolean isValid(String name, ConstraintValidatorContext context) {
        return name == null || isNull(busStopService.getBusStopByName(name));
    }

    public void initialize(UniqueBusStopName constraint) {
    }

}
