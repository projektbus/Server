package ProjektBus.Server.validation.validator;

import ProjektBus.Server.service.CarrierService;
import ProjektBus.Server.validation.validationInterfaces.UniqueCarrierName;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.util.Objects.isNull;

public class UniqueCarrierNameValidator implements ConstraintValidator<UniqueCarrierName, String> {

    @Autowired
    private CarrierService carrierService;

    public boolean isValid(String name, ConstraintValidatorContext context) {
        return name == null || isNull(carrierService.getCarrierByName(name));
    }

    public void initialize(UniqueCarrierName constraint) {
    }
}
