package ProjektBus.Server.service;

import ProjektBus.Server.model.BusConnection;
import ProjektBus.Server.repository.BusConnectionRepository;
import ProjektBus.Server.service.interfaces.BusConnectionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BusConnectionServiceImpl implements BusConnectionService {
    @Autowired
    private BusConnectionRepository busConnectionRepository;

    @Override
    public BusConnection addBusConnection(BusConnection busConnection) {
        return busConnectionRepository.save(busConnection);
    }

    @Override
    public void deleteBusConnection(BusConnection busConnection) {
        busConnectionRepository.delete(busConnection);
    }

    @Override
    public BusConnection getBusConnectionById(String id) {
        return busConnectionRepository.findById(id).isPresent() ? busConnectionRepository.findById(id).get() : null;
    }

    @Override
    public List<BusConnection> getAllBusConnections() {
        return busConnectionRepository.findAll();
    }

    @Override
    public List<BusConnection> getBusConnectionByLineAndStop(String busLineId, String busStopId) {
        return busConnectionRepository.findByBusLineIdAndBusStopId(busLineId, busStopId);
    }

    @Override
    public List<BusConnection> getBusConnectionByLineAndCarrier(String busLineId, String carrierId) {
        return busConnectionRepository.findByBusLineIdAndCarrierId(busLineId, carrierId);
    }

}
