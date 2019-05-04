package ProjektBus.Server.repository;

import ProjektBus.Server.model.BusStop;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BusStopRepository extends MongoRepository<BusStop, String> {

    BusStop findByName(String name);

}
