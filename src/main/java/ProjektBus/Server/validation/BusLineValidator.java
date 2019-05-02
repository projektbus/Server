package ProjektBus.Server.validation;

import ProjektBus.Server.model.BusLine;
import ProjektBus.Server.service.BusLineService;
import ProjektBus.Server.service.BusStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
public class BusLineValidator implements Validator {

    @Autowired
    private BusLineService busLineService;

    @Autowired
    private BusStopService busStopService;

    @Override
    public boolean supports(Class<?> clazz) {
        return BusLine.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BusLine busLine = (BusLine) target;
        if (nonNull(busLineService.getBusLineByName(busLine.getName()))) {
            errors.reject("name", "Bus line already exists");
        }

        if(isNull(busStopService.getBusStopById(busLine.getStartBusStopId()))) {
            errors.reject("name", "Start bus stop does not exists");
        }

        if(isNull(busStopService.getBusStopById(busLine.getEndBusStopId()))) {
            errors.reject("name", "End bus stop does not exists");
        }

    }
}
