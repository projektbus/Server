package ProjektBus.Server.service;

import ProjektBus.Server.model.User;
import ProjektBus.Server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        user = userRepository.save(user);
        return user;
    }

    @Override
    public void deleteUser(User user){
        userRepository.delete(user);
    }

    @Override
    public User updatePassword(User user, String password) {
        user.setPassword(password);
        user=userRepository.save(user);
        return user;
    }
}
