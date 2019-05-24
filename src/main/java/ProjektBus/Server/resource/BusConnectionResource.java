package ProjektBus.Server.resource;

import ProjektBus.Server.model.BusConnection;
import ProjektBus.Server.service.BusConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class BusConnectionResource {
    @Autowired
    private BusConnectionService busConnectionService;

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @PostMapping("/bus-connections")
    public ResponseEntity saveBusConnection(@Valid @RequestBody BusConnection busConnection) throws URISyntaxException {
        busConnectionService.addBusConnection(busConnection);
        return ResponseEntity.created(new URI("https://peaceful-sierra-14544.herokuapp.com/bus-connections/" + busConnection.getId())).build();
    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/bus-connections")
    public @ResponseBody ResponseEntity getBusConnections() {
        return new ResponseEntity(busConnectionService.getAllBusConnections(), HttpStatus.OK);

    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @DeleteMapping("/bus-connections/{id}")
    public ResponseEntity deleteBusConnection(@PathVariable("id") String id) {
        BusConnection busConnection = busConnectionService.getBusConnectionById(id);
        if (busConnection!=null) {
            busConnectionService.deleteBusConnection(busConnection);
            return new ResponseEntity("Bus conncection deleted successfully", HttpStatus.OK);
        }
        else {
            return new ResponseEntity("Bus conncection does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/bus-connections/{id}")
    public @ResponseBody ResponseEntity getBusConnection(@PathVariable("id") String id)  {
        if (null != busConnectionService.getBusConnectionById(id)) {
            return new ResponseEntity(busConnectionService.getBusConnectionById(id), HttpStatus.OK);
        }
        else {
            return new ResponseEntity("Bus connection does not exist", HttpStatus.NOT_FOUND);
        }

    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/bus-connections/bus-lines/{busLineId}/bus-stops/{busStopId}")
    public @ResponseBody ResponseEntity getBusConnectionByBusLineAndBusStop(@PathVariable("busLineId") String busLineId,@PathVariable("busStopId") String busStopId)  {
        if (null != busConnectionService.getBusConnectionByLineAndStop(busLineId, busStopId)) {
            return new ResponseEntity(busConnectionService.getBusConnectionByLineAndStop(busLineId, busStopId), HttpStatus.OK);
        }
        else {
            return new ResponseEntity("Bus connection does not exist", HttpStatus.NOT_FOUND);
        }

    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/bus-connections/bus-lines/{busLineId}/bus-carriers/{carrierId}")
    public @ResponseBody ResponseEntity getBusLineByBusLineAndCarrier(@PathVariable("busLineId") String busLineId, @PathVariable("carrierId") String carrierId)  {
        if (null != busConnectionService.getBusConnectionByLineAndCarrier(busLineId, carrierId)) {
            return new ResponseEntity(busConnectionService.getBusConnectionByLineAndCarrier(busLineId, carrierId), HttpStatus.OK);
        }
        else {
            return new ResponseEntity("Bus connection does not exist", HttpStatus.NOT_FOUND);
        }

    }



}
