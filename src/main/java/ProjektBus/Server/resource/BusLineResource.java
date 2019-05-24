package ProjektBus.Server.resource;

import ProjektBus.Server.model.BusLine;
import ProjektBus.Server.model.BusStop;
import ProjektBus.Server.service.BusLineService;
import ProjektBus.Server.service.BusStopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class BusLineResource {

    @Autowired
    private BusLineService busLineService;

    @Autowired
    private BusStopService busStopService;

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/bus-lines")
    public @ResponseBody ResponseEntity getBusLines() {
        return new ResponseEntity(busLineService.getAllBusLines(), HttpStatus.OK);

    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @PostMapping("/bus-lines")
    public ResponseEntity saveBusLine(@Valid @RequestBody BusLine busLine) throws URISyntaxException {
        busLineService.addBusLine(busLine);

        return ResponseEntity.created(new URI("https://peaceful-sierra-14544.herokuapp.com/bus-lines/" + busLine.getId())).build();
    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @PostMapping("/bus-lines/{busLineId}/bus-stops/{busStopId}")
    public ResponseEntity addBusStopToBusLine(@PathVariable("busLineId") String busLineId, @PathVariable("busStopId") String busStopId ) {
        BusLine busLine = busLineService.getBusLineById(busLineId);
        BusStop busStop = busStopService.getBusStopById(busStopId);

        if(busLine == null) {
            return new ResponseEntity("Bus line does not exist", HttpStatus.NOT_FOUND);
        }
        else if(busStop == null) {
            return new ResponseEntity("Bus stop does not exist", HttpStatus.NOT_FOUND);
        }
        else if(busLine.isBusStopOnList(busStopId)) {
            return new ResponseEntity("Bus stop already added", HttpStatus.CONFLICT);
        }
        else {
            busLine.addBusStopToList(busStop);
            busLineService.addBusLine(busLine);
            return new ResponseEntity("Bus stop added to Bus line", HttpStatus.OK);
        }
    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @DeleteMapping("/bus-lines/{busLineId}/bus-stops/{busStopId}")
    public ResponseEntity deleteBusStopFromBusLine(@PathVariable("busLineId") String busLineId, @PathVariable("busStopId") String busStopId ) {
        BusLine busLine = busLineService.getBusLineById(busLineId);
        BusStop busStop = busStopService.getBusStopById(busStopId);

        if(busLine == null) {
            return new ResponseEntity("Bus line does not exist", HttpStatus.NOT_FOUND);
        }
        else if(busStop == null) {
            return new ResponseEntity("Bus stop does not exist", HttpStatus.NOT_FOUND);
        }
        else if(!busLine.isBusStopOnList(busStopId)) {
            return new ResponseEntity("Bus stop not on list", HttpStatus.CONFLICT);
        }
        else {
            busLine.deleteBusStopFromList(busStop);
            busLineService.addBusLine(busLine);
            return new ResponseEntity("Bus stop deleted from bus line", HttpStatus.OK);
        }
    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/bus-lines/{id}")
    public @ResponseBody ResponseEntity getBusLine(@PathVariable("id") String id)  {
        if (null != busLineService.getBusLineById(id)) {
            return new ResponseEntity(busLineService.getBusLineById(id), HttpStatus.OK);
        }
        else {
            return new ResponseEntity("Bus line does not exist", HttpStatus.NOT_FOUND);
        }

    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @DeleteMapping("/bus-lines/{id}")
    public @ResponseBody ResponseEntity deleteBusLine(@PathVariable("id") String id)  {
        BusLine busLine = busLineService.getBusLineById(id);
        if (busLine != busLineService.getBusLineById(id)) {
            busLineService.deleteBusLine(busLine);
            return new ResponseEntity("Bus line deleted successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity("Bus line does not exist", HttpStatus.NOT_FOUND);
        }
    }

}
