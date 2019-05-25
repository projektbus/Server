package ProjektBus.Server.service;

import ProjektBus.Server.model.BusStop;
import ProjektBus.Server.repository.BusStopRepository;
import ProjektBus.Server.service.interfaces.BusStopService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BusStopServiceImpl implements BusStopService {

    @Autowired
    private BusStopRepository busStopRepository;

    @Override
    public BusStop addBusStop(BusStop busStop) {
        return busStopRepository.save(busStop);
    }

    @Override
    public BusStop getBusStopByName(String name) {
        return busStopRepository.findByName(name);
    }

    @Override
    public BusStop getBusStopById(String id) {
        return busStopRepository.findById(id).isPresent() ? busStopRepository.findById(id).get() : null;
    }

    @Override
    public List<BusStop> getAllBusStops() {
        return busStopRepository.findAll();
    }

    @Override
    public void deleteBusStop(BusStop busStop) {
        busStopRepository.delete(busStop);
    }
}
