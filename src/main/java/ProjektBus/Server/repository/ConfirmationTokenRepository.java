package ProjektBus.Server.repository;

import ProjektBus.Server.model.ConfirmationToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfirmationTokenRepository extends MongoRepository<ConfirmationToken, String> {

    ConfirmationToken findByTokenCode(String code);
}
