package ProjektBus.Server.resource;

import ProjektBus.Server.model.BusLine;
import ProjektBus.Server.model.BusStop;
import ProjektBus.Server.service.interfaces.BusLineService;
import ProjektBus.Server.service.interfaces.BusStopService;
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
public class BusLineResource {

    @Autowired
    private BusLineService busLineService;

    @Autowired
    private BusStopService busStopService;

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/bus-lines")
    public @ResponseBody ResponseEntity getBusLines() {
        return new ResponseEntity<>(busLineService.getAllBusLines(), HttpStatus.OK);

    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @PostMapping("/bus-lines")
    public ResponseEntity saveBusLine(@Valid @RequestBody BusLine busLine) throws URISyntaxException {
        busLineService.addBusLine(busLine);

        return ResponseEntity.created(new URI("https://peaceful-sierra-14544.herokuapp.com/bus-lines/" + busLine.getName())).build();
    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @PostMapping("/bus-lines/{busLineId}/bus-stops/{busStopId}")
    public ResponseEntity addBusStopToBusLine(@PathVariable("busLineId") String busLineId, @PathVariable("busStopId") String busStopId ) {
        BusLine busLine = busLineService.getBusLineById(busLineId);
        BusStop busStop = busStopService.getBusStopById(busStopId);

        if(busLine == null) {
            return new ResponseEntity<>(new ApplicationError(ErrorCodes.BUS_LINE_NOT_FOUND), HttpStatus.NOT_FOUND);
        }
        else if(busStop == null) {
            return new ResponseEntity<>(new ApplicationError(ErrorCodes.BUS_STOP_NOT_FOUND), HttpStatus.NOT_FOUND);
        }
        else if(busLine.isBusStopOnList(busStopId)) {
            return new ResponseEntity<>(new ApplicationError(ErrorCodes.BUS_STOP_ALREADY_ADDED), HttpStatus.CONFLICT);
        }
        else {
            busLine.addBusStopToList(busStop);
            busLineService.addBusLine(busLine);
            return new ResponseEntity<>(new ApplicationResponse(ErrorCodes.BUS_STOP_ADDED_TO_BUS_LINE), HttpStatus.OK);
        }
    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @DeleteMapping("/bus-lines/{busLineId}/bus-stops/{busStopId}")
    public ResponseEntity deleteBusStopFromBusLine(@PathVariable("busLineId") String busLineId, @PathVariable("busStopId") String busStopId ) {
        BusLine busLine = busLineService.getBusLineById(busLineId);
        BusStop busStop = busStopService.getBusStopById(busStopId);

        if(busLine == null) {
            return new ResponseEntity<>(new ApplicationError(ErrorCodes.BUS_LINE_NOT_FOUND), HttpStatus.NOT_FOUND);
        }
        else if(busStop == null) {
            return new ResponseEntity<>(new ApplicationError(ErrorCodes.BUS_STOP_NOT_FOUND), HttpStatus.NOT_FOUND);
        }
        else if(!busLine.isBusStopOnList(busStopId)) {
            return new ResponseEntity<>(new ApplicationError(ErrorCodes.BUS_STOP_NOT_ON_LIST), HttpStatus.CONFLICT);
        }
        else {
            busLine.deleteBusStopFromList(busStop);
            busLineService.addBusLine(busLine);
            return new ResponseEntity<>(new ApplicationResponse(ErrorCodes.BUS_STOP_DELETED_FROM_BUS_LINE), HttpStatus.OK);
        }
    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/bus-lines/{name}")
    public @ResponseBody ResponseEntity getBusLine(@PathVariable("name") String name)  {
        if (null != busLineService.getBusLineByName(name)) {
            return new ResponseEntity<>(busLineService.getBusLineByName(name), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new ApplicationError(ErrorCodes.BUS_LINE_NOT_FOUND), HttpStatus.NOT_FOUND);
        }

    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @DeleteMapping("/bus-lines/{name}")
    public @ResponseBody ResponseEntity deleteBusLine(@PathVariable("name") String name)  {
        BusLine busLine = busLineService.getBusLineByName(name);
        if (busLine != busLineService.getBusLineByName(name)) {
            busLineService.deleteBusLine(busLine);
            return new ResponseEntity<>(new ApplicationResponse(ErrorCodes.BUS_LINE_DELETE_SUCCESSFUL), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new ApplicationError(ErrorCodes.BUS_LINE_NOT_FOUND), HttpStatus.NOT_FOUND);
        }
    }

}
