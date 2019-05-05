package ProjektBus.Server.repository;
import ProjektBus.Server.model.ResetToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResetTokenRepository extends MongoRepository<ResetToken, String> {

    ResetToken findByTokenCode(String code);
}
