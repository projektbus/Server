package ProjektBus.Server.service;

import ProjektBus.Server.model.BusStop;

import java.util.List;

public interface BusStopService {
    BusStop addBusStop(BusStop busStop);
    BusStop getBusStopByName(String name);
    BusStop getBusStopById(String id);
    List<BusStop> getAllBusStops();
    void deleteBusStop(BusStop busStop);

}
