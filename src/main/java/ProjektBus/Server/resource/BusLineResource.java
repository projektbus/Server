package ProjektBus.Server.resource;

import ProjektBus.Server.model.BusLine;
import ProjektBus.Server.model.BusStop;
import ProjektBus.Server.service.BusLineService;
import ProjektBus.Server.validation.BusLineValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class BusLineResource {

    @Autowired
    private BusLineService busLineService;

    @Autowired
    private BusLineValidator busLineValidator;

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @PostMapping("/bus-lines")
    public ResponseEntity saveBusLine(@Valid @RequestBody BusLine busLine) throws URISyntaxException {
        busLineService.addBusLine(busLine);

        return ResponseEntity.created(new URI("https://peaceful-sierra-14544.herokuapp.com/busLine?name=" + busLine.getName())).build();
    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/bus-lines/{name}")
    public @ResponseBody ResponseEntity getBusLine(@PathVariable("name") String name)  {
        if (null != busLineService.getBusLineByName(name)) {
            return new ResponseEntity(busLineService.getBusLineByName(name), HttpStatus.OK);
        }
        else {
            return new ResponseEntity("Bus line does not exist", HttpStatus.NOT_FOUND);
        }

    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/bus-lines")
    public @ResponseBody ResponseEntity getBusLines() {
        return new ResponseEntity(busLineService.getAllBusLines(), HttpStatus.OK);

    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @DeleteMapping("/bus-lines/{name}")
    public @ResponseBody ResponseEntity deleteBusLine(@PathVariable("name") String name)  {
        BusLine busLine = busLineService.getBusLineByName(name);
        if (busLine != busLineService.getBusLineByName(name)) {
            busLineService.deleteBusLine(busLine);
            return new ResponseEntity(busLine, HttpStatus.OK);
        }
        else {
            return new ResponseEntity("Bus line does not exist", HttpStatus.NOT_FOUND);
        }
    }


    @InitBinder("bus-lines")
    public void setupBinder(WebDataBinder binder) {
        binder.addValidators(busLineValidator);
    }
}
