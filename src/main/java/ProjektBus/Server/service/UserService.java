package ProjektBus.Server.service;

import ProjektBus.Server.model.User;
import java.util.List;


public interface UserService {

    User registerUser(User user);
    void deleteUser(User user);
    User getUserById(String id);
    User getUserByLogin(String login);
    User getUserByEmail(String email);
    List<User> getAllUsers();
}
