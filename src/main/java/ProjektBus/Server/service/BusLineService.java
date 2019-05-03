package ProjektBus.Server.service;

import ProjektBus.Server.model.BusLine;

import java.util.List;

public interface BusLineService {
    BusLine addBusLine(BusLine busLine);
    BusLine getBusLineByName(String name);
    List<BusLine> getAllBusLines();
}

