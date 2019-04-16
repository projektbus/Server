package ProjektBus.Server.service;

import ProjektBus.Server.model.User;
import ProjektBus.Server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public User registerUser(@Valid @RequestBody User user) {
        Errors result = null;
        user.validate(user,result);
        System.out.println("poszlo");
        user = userRepository.save(user);
        return user;

    }

}
