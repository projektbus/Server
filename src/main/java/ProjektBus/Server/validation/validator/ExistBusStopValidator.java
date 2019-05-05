package ProjektBus.Server.validation.validator;

import javax.validation.ConstraintValidator;

import ProjektBus.Server.repository.BusStopRepository;
import ProjektBus.Server.validation.validationInterfaces.ExistBusStop;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidatorContext;

public class ExistBusStopValidator implements ConstraintValidator<ExistBusStop, String> {

    @Autowired
    private BusStopRepository busStopRepository;

    public boolean isValid(String id, ConstraintValidatorContext context) {
        return id != null && busStopRepository.findById(id).isPresent();
    }

    public void initialize(ExistBusStop constraint) {
    }

}
