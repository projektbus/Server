package ProjektBus.Server.service;

import ProjektBus.Server.model.BusLine;

import java.util.List;

public interface BusLineService {
    BusLine addBusLine(BusLine busLine);
    BusLine getBusLineByName(String name);
    void deleteBusLine(BusLine busLine);
    List<BusLine> getAllBusLines();
}

