package ProjektBus.Server.validation.validator;

import ProjektBus.Server.service.interfaces.BusLineService;
import ProjektBus.Server.validation.validationInterfaces.ExistBusLine;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.util.Objects.nonNull;

public class ExistBusLineValidator implements ConstraintValidator<ExistBusLine, String> {

    @Autowired
    private BusLineService busLineService;

    public boolean isValid(String id, ConstraintValidatorContext context) {
        return id != null && nonNull(busLineService.getBusLineById(id));
    }

    public void initialize(ExistBusLine constraint) {
    }

}
