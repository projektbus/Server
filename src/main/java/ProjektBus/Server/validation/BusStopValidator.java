package ProjektBus.Server.validation;

import ProjektBus.Server.model.BusStop;
import ProjektBus.Server.service.BusStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

import static java.util.Objects.nonNull;


@Component
public class BusStopValidator implements Validator {

    @Autowired
    private BusStopService busStopService;

    @Override
    public boolean supports(Class<?> clazz) {
        return BusStop.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BusStop busStop = (BusStop) target;
        if(nonNull(busStopService.getBusStopByName(busStop.getName()))) {
            errors.reject("name","Bus stop already exists");
        }

        String nameRegex = "[A-Za-z]+[a-zA-Z0-9]*";
        if(!Pattern.compile(nameRegex).matcher(busStop.getName()).matches()) {
            errors.reject("name","Name must start with letter");
        }
    }
}
