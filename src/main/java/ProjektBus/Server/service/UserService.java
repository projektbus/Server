package ProjektBus.Server.service;

import ProjektBus.Server.model.User;

public interface UserService {

    User registerUser(User user);
    void deleteUser(User user);
    User updatePassword(User user, String password);
}
