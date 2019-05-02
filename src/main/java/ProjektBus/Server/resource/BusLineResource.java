package ProjektBus.Server.resource;

import ProjektBus.Server.model.BusLine;
import ProjektBus.Server.service.BusLineService;
import ProjektBus.Server.validation.BusLineValidator;
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
public class BusLineResource {

    @Autowired
    private BusLineService busLineService;

    @Autowired
    private BusLineValidator busLineValidator;

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @PostMapping("/busLine")
    public ResponseEntity saveBusLine(@Valid @RequestBody BusLine busLine) throws URISyntaxException {
        busLineService.addBusLine(busLine);

        return ResponseEntity.created(new URI("https://peaceful-sierra-14544.herokuapp.com/busLine?name=" + busLine.getName())).build();
    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/busLine")
    public @ResponseBody ResponseEntity getBusLine(@RequestParam("name") String name)  {
        if (null != busLineService.getBusLineByName(name)) {
            return new ResponseEntity(busLineService.getBusLineByName(name), HttpStatus.OK);
        }
        else {
            return new ResponseEntity("Bus line does not exist", HttpStatus.NOT_FOUND);
        }

    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/busLines")
    public @ResponseBody ResponseEntity getBusLines() {
        return new ResponseEntity(busLineService.getAllBusLines(), HttpStatus.OK);

    }


    @InitBinder("busLine")
    public void setupBinder(WebDataBinder binder) {
        binder.addValidators(busLineValidator);
    }
}
