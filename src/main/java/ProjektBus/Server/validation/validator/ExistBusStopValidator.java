package ProjektBus.Server.validation.validator;

import javax.validation.ConstraintValidator;

import ProjektBus.Server.service.BusStopService;
import ProjektBus.Server.validation.validationInterfaces.ExistBusStop;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidatorContext;

import static java.util.Objects.nonNull;


public class ExistBusStopValidator implements ConstraintValidator<ExistBusStop, String> {

    @Autowired
    private BusStopService busStopService;

    public boolean isValid(String id, ConstraintValidatorContext context) {
        return id != null && nonNull(busStopService.getBusStopById(id));
    }

    public void initialize(ExistBusStop constraint) {
    }

}
