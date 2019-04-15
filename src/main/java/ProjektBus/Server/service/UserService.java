package ProjektBus.Server.service;
import ProjektBus.Server.model.User;

public interface UserService {

    User registerUser(User user);

    Integer userValidate( User user);
}
