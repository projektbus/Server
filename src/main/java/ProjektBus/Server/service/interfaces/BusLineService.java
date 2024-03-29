package ProjektBus.Server.service.interfaces;

import ProjektBus.Server.model.BusLine;

import java.util.List;

public interface BusLineService {
    BusLine addBusLine(BusLine busLine);
    BusLine getBusLineByName(String name);
    BusLine getBusLineById(String id);
    void deleteBusLine(BusLine busLine);
    List<BusLine> getAllBusLines();
}

