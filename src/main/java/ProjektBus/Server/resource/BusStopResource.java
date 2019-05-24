package ProjektBus.Server.resource;

import ProjektBus.Server.model.BusStop;
import ProjektBus.Server.service.BusStopService;
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
    @GetMapping("/bus-stops/{id}")
    public @ResponseBody ResponseEntity getBusStop(@PathVariable("id") String id)  {
        if (null != busStopService.getBusStopById(id)) {
            return new ResponseEntity(busStopService.getBusStopById(id), HttpStatus.OK);
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
    @DeleteMapping("/bus-stops/{id}")
    public @ResponseBody ResponseEntity deleteBusStop(@PathVariable("id") String id)  {
        BusStop busStop = busStopService.getBusStopById(id);
        if (busStop != busStopService.getBusStopById(id)) {
            busStopService.deleteBusStop(busStop);
            return new ResponseEntity("Bus stop deleted successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity("Bus stop does not exist", HttpStatus.NOT_FOUND);
        }
    }

}
