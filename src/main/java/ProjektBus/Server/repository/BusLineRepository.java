package ProjektBus.Server.repository;

import ProjektBus.Server.model.BusLine;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BusLineRepository extends MongoRepository<BusLine, String> {
    BusLine findByName(String name);
}
