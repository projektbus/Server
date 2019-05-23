package ProjektBus.Server.service;

import ProjektBus.Server.model.Carrier;
import ProjektBus.Server.repository.CarrierRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CarrierServiceImpl implements CarrierService {

    @Autowired
    private CarrierRepository carrierRepository;

    @Override
    public Carrier addCarrier(Carrier carrier) {
        return carrierRepository.save(carrier);
    }

    @Override
    public void deleteCarrier(Carrier carrier) {
        carrierRepository.delete(carrier);

    }

    @Override
    public Carrier getCarrierById(String id) {
        return carrierRepository.findById(id).isPresent() ? carrierRepository.findById(id).get() : null ;
    }

    @Override
    public Carrier getCarrierByName(String name) {
        return carrierRepository.findByName(name);
    }

    @Override
    public List<Carrier> getAllCarriers() {
        return carrierRepository.findAll();
    }
}
