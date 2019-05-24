package ProjektBus.Server.service;

import ProjektBus.Server.model.BusLine;
import ProjektBus.Server.repository.BusLineRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BusLineServiceImpl implements BusLineService {

    @Autowired
    private BusLineRepository busLineRepository;

    @Override
    public BusLine getBusLineById(String id) {
        return busLineRepository.findById(id).isPresent() ? busLineRepository.findById(id).get() : null ;
    }

    @Override
    public BusLine addBusLine(BusLine busLine) {
        return busLineRepository.save(busLine);
    }

    @Override
    public BusLine getBusLineByName(String name) {
        return busLineRepository.findByName(name);
    }

    @Override
    public void deleteBusLine(BusLine busLine) {
        busLineRepository.delete(busLine);
    }

    @Override
    public List<BusLine> getAllBusLines() {
        return busLineRepository.findAll();
    }
}
