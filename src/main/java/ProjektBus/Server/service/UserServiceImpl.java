package ProjektBus.Server.service;

import ProjektBus.Server.model.User;
import ProjektBus.Server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        user = userRepository.save(user);
        return user;
    }

}
