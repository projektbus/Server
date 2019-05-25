package ProjektBus.Server.service.interfaces;

import ProjektBus.Server.model.BusConnection;

import java.util.List;

public interface BusConnectionService {
    BusConnection addBusConnection(BusConnection busConnection);
    void deleteBusConnection(BusConnection busConnection);
    BusConnection getBusConnectionById(String id);
    List<BusConnection> getAllBusConnections();
    List<BusConnection> getBusConnectionByLineAndStop(String busLineId, String busStopId);
    List<BusConnection> getBusConnectionByLineAndCarrier(String busLineId, String carrierId);
}
