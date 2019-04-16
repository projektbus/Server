package ProjektBus.Server.service;

import ProjektBus.Server.model.User;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface UserService {

    User registerUser(@Valid @RequestBody User user);

}
