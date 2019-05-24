package ProjektBus.Server.resource;

import ProjektBus.Server.model.Carrier;
import ProjektBus.Server.service.CarrierService;
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
public class CarrierResource {

    @Autowired
    private CarrierService carrierService;

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/carriers")
    public @ResponseBody
    ResponseEntity getCarriers() {
        return new ResponseEntity<>(carrierService.getAllCarriers(), HttpStatus.OK);

    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @PostMapping("/carriers")
    public ResponseEntity addCarrier(@Valid @RequestBody Carrier carrier) throws URISyntaxException {
        carrierService.addCarrier(carrier);

        return ResponseEntity.created(new URI("https://peaceful-sierra-14544.herokuapp.com/carriers/" + carrier.getId())).build();
    }


    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @GetMapping("/carriers/{id}")
    public @ResponseBody ResponseEntity getCarrier(@PathVariable("id") String id)  {
        if (null != carrierService.getCarrierById(id)) {
            return new ResponseEntity<>(carrierService.getCarrierById(id), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new ApplicationError(ErrorCodes.CARRIER_NOT_FOUND), HttpStatus.NOT_FOUND);
        }

    }

    @CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
    @DeleteMapping("/carriers/{id}")
    public @ResponseBody ResponseEntity deleteCarrier(@PathVariable("id") String id)  {
        Carrier carrier = carrierService.getCarrierById(id);
        if (carrier != carrierService.getCarrierById(id)) {
            carrierService.deleteCarrier(carrier);
            return new ResponseEntity<>(new ApplicationResponse(ErrorCodes.CARRIER_DELETE_SUCCESSFUL), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new ApplicationError(ErrorCodes.CARRIER_NOT_FOUND), HttpStatus.NOT_FOUND);
        }
    }
}
