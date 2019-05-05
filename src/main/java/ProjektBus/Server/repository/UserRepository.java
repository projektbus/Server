package ProjektBus.Server.repository;


import ProjektBus.Server.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface UserRepository extends MongoRepository<User, String> {

    User findByLogin(String login);
    User findByEmail(String email);
}
