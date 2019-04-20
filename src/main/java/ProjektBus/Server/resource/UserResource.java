package ProjektBus.Server.resource;

import ProjektBus.Server.model.User;
import ProjektBus.Server.model.UserCreateRequest;
import ProjektBus.Server.repository.UserRepository;
import ProjektBus.Server.model.UserCreateRequestValidator;
import ProjektBus.Server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
public class UserResource {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserCreateRequestValidator userCreateRequestValidator;

    @PostMapping("/user")
    public ResponseEntity saveUser(@RequestBody @Valid UserCreateRequest userCreateRequest) {
        User user = userCreateRequest.toUser();
        userService.registerUser(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @InitBinder("userCreateRequest")
    public void setupBinder(WebDataBinder binder) {
        binder.addValidators(userCreateRequestValidator);
    }

    @GetMapping("/user")
    public @ResponseBody ResponseEntity userGet(@RequestParam("login") String login)  {
        if (null != userRepository.findByLogin(login)) {
            return new ResponseEntity(userRepository.findByLogin(login), HttpStatus.OK);
        }
        else if (null!=userRepository.findByEmail(login)) {
            return new ResponseEntity(userRepository.findByEmail(login), HttpStatus.OK);
        }
        else{
            return new ResponseEntity("USER DOES NOT EXIST", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/users")
    public @ResponseBody ResponseEntity usersGet() {
        return new ResponseEntity(userRepository.findAll(), HttpStatus.OK);

    }

}
