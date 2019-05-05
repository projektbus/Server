package ProjektBus.Server.validation.validator;

import javax.validation.ConstraintValidator;

import ProjektBus.Server.repository.BusLineRepository;
import ProjektBus.Server.validation.validationInterfaces.UniqueBusLineName;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidatorContext;

import static java.util.Objects.isNull;

public class UniqueBusLineNameValidator implements ConstraintValidator<UniqueBusLineName, String> {

    @Autowired
    private BusLineRepository busLineRepository;

    public boolean isValid(String name, ConstraintValidatorContext context) {
        return name == null || isNull(busLineRepository.findByName(name));
    }

    public void initialize(UniqueBusLineName constraint) {
    }

}
