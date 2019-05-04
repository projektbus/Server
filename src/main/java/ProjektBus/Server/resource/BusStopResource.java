package ProjektBus.Server.resource;

import ProjektBus.Server.model.BusStop;
import ProjektBus.Server.service.BusStopService;
import ProjektBus.Server.validation.BusStopValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class BusStopResource {

    @Autowired
    private BusStopService busStopService;

    @Autowired
    private BusStopValidator busStopValidator;

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @PostMapping("/bus-stops")
    public ResponseEntity saveBusStop(@Valid @RequestBody BusStop busStop) throws URISyntaxException {
        busStopService.addBusStop(busStop);

        return ResponseEntity.created(new URI("https://peaceful-sierra-14544.herokuapp.com/busStop?name=" + busStop.getName())).build();
    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/bus-stops/{name}")
    public @ResponseBody ResponseEntity getBusStop(@PathVariable("name") String name)  {
        if (null != busStopService.getBusStopByName(name)) {
            return new ResponseEntity(busStopService.getBusStopByName(name), HttpStatus.OK);
        }
        else {
            return new ResponseEntity("Bus stop does not exist", HttpStatus.NOT_FOUND);
        }

    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/bus-stops")
    public @ResponseBody ResponseEntity getBusStops() {
        return new ResponseEntity(busStopService.getAllBusStops(), HttpStatus.OK);

    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @DeleteMapping("/bus-stops/{name}")
    public @ResponseBody ResponseEntity deleteBusStop(@PathVariable("name") String name)  {
        BusStop busStop = busStopService.getBusStopByName(name);
        if (busStop != busStopService.getBusStopByName(name)) {
            busStopService.deleteBusStop(busStop);
            return new ResponseEntity(busStop, HttpStatus.OK);
        }
        else {
            return new ResponseEntity("Bus stop does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @InitBinder("busStop")
    public void setupBinder(WebDataBinder binder) {
        binder.addValidators(busStopValidator);
    }

}
