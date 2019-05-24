package ProjektBus.Server.repository;

import ProjektBus.Server.model.BusConnection;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BusConnectionRepository extends MongoRepository<BusConnection, String> {
    BusConnection findByBusLineId(String busLineId);
    BusConnection findByBusStopId(String busStopId);
    List<BusConnection> findByBusLineIdAndBusStopId(String busLineId, String busStopId);
    List<BusConnection> findByBusLineIdAndCarrierId(String busLineId, String carrierId);
}
