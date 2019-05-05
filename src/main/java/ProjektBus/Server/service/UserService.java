package ProjektBus.Server.service;

import ProjektBus.Server.model.User;
import org.springframework.stereotype.Service;

public interface UserService {

    User registerUser(User user);
    void deleteUser(User user);
    User updatePassword(User user, String password);
}
