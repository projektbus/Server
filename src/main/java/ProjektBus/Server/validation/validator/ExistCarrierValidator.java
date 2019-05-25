package ProjektBus.Server.validation.validator;

import ProjektBus.Server.service.CarrierService;
import ProjektBus.Server.validation.validationInterfaces.ExistCarrier;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.util.Objects.nonNull;

public class ExistCarrierValidator implements ConstraintValidator<ExistCarrier, String> {
    @Autowired
    private CarrierService carrierService;

    public boolean isValid(String id, ConstraintValidatorContext context) {
        return id != null && nonNull(carrierService.getCarrierById(id));
    }

    public void initialize(ExistCarrier constraint) {
    }
}
