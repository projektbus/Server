package ProjektBus.Server.repository;
import ProjektBus.Server.model.SettingPasswordToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SettingPasswordTokenRepository extends MongoRepository<SettingPasswordToken, String> {

    SettingPasswordToken findByTokenCode(String code);
}
