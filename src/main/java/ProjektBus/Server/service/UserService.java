package ProjektBus.Server.service;

import ProjektBus.Server.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    User registerUser(User user);
<<<<<<< HEAD
    void deleteUser(User user);
    User updatePassword(User user, String password);
=======
    User getUserById(String id);
    User getUserByLogin(String login);
    User getUserByEmail(String email);
    List<User> getAllUsers();
>>>>>>> 36cbaeeca4e56fb2e527492bac37b9e232c9cd7a
}
