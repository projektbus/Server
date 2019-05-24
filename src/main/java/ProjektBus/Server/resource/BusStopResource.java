package ProjektBus.Server.resource;

import ProjektBus.Server.model.BusStop;
import ProjektBus.Server.service.BusStopService;
import ProjektBus.Server.utils.ApplicationError;
import ProjektBus.Server.utils.ApplicationResponse;
import ProjektBus.Server.utils.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class BusStopResource {

    @Autowired
    private BusStopService busStopService;

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @PostMapping("/bus-stops")
    public ResponseEntity saveBusStop(@Valid @RequestBody BusStop busStop) throws URISyntaxException {
        busStopService.addBusStop(busStop);

        return ResponseEntity.created(new URI("https://peaceful-sierra-14544.herokuapp.com/bus-stops/" + busStop.getName())).build();
    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/bus-stops/{name}")
    public @ResponseBody ResponseEntity getBusStop(@PathVariable("name") String name)  {
        if (null != busStopService.getBusStopByName(name)) {
            return new ResponseEntity<>(busStopService.getBusStopByName(name), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new ApplicationError(ErrorCodes.BUS_STOP_NOT_FOUND), HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/bus-stops")
    public @ResponseBody ResponseEntity getBusStops() {
        return new ResponseEntity<>(busStopService.getAllBusStops(), HttpStatus.OK);

    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @DeleteMapping("/bus-stops/{name}")
    public @ResponseBody ResponseEntity deleteBusStop(@PathVariable("name") String name)  {
        BusStop busStop = busStopService.getBusStopByName(name);
        if (busStop != busStopService.getBusStopByName(name)) {
            busStopService.deleteBusStop(busStop);
            return new ResponseEntity<>(new ApplicationResponse(ErrorCodes.BUS_STOP_DELETE_SUCCESSFUL), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new ApplicationError(ErrorCodes.BUS_STOP_NOT_FOUND), HttpStatus.NOT_FOUND);
        }
    }

}
