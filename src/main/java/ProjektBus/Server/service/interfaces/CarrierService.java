package ProjektBus.Server.service.interfaces;

import ProjektBus.Server.model.Carrier;

import java.util.List;

public interface  CarrierService {
    Carrier addCarrier(Carrier carrier);
    void deleteCarrier(Carrier carrier);
    Carrier getCarrierById(String id);
    Carrier getCarrierByName(String name);
    List<Carrier> getAllCarriers();

}
