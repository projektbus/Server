package ProjektBus.Server.service;

import ProjektBus.Server.model.BusConnection;

import java.util.List;

public interface BusConnectionService {
    BusConnection addBusConnection(BusConnection busConnection);
    void deleteBusConnection(BusConnection busConnection);
    List<BusConnection> getAllBusConnections();
    List<BusConnection> getBusConnectionByLineAndStop(String busLineId, String busStopId);
    List<BusConnection> getBusConnectionByLineAndCarrier(String busLineId, String carrierId);
}
