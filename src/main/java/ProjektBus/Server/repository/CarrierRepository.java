package ProjektBus.Server.repository;

import ProjektBus.Server.model.Carrier;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarrierRepository extends MongoRepository<Carrier, String> {
    Carrier findByName(String name);

}
