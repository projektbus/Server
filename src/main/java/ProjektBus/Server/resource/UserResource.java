package ProjektBus.Server.resource;

import ProjektBus.Server.mapper.JsonMapper;
import ProjektBus.Server.model.User;
import ProjektBus.Server.repository.UserRepository;
import ProjektBus.Server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserResource {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    public ResponseEntity saveUser(@RequestBody User user) {

        userService.registerUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping("/user")
    public @ResponseBody ResponseEntity<String> userGet(@RequestParam("login") String login)  {
        User user;
        String retVal;
        if (null != userRepository.findByLogin(login)) {
            user = userRepository.findByLogin(login);
            retVal = JsonMapper.userMapper(user);
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        }
        else if (null!=userRepository.findByEmail(login)) {
            user = userRepository.findByEmail(login);
            retVal = JsonMapper.userMapper(user);
            return new ResponseEntity<>(retVal, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("USER DOES NOT EXIST", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/users")
    public @ResponseBody ResponseEntity<String> userGet() {
        String retVal;
        List<User> userList;
        userList=userRepository.findAll();
        retVal=JsonMapper.listMapper(userList);
        return new ResponseEntity<>(retVal, HttpStatus.OK);

    }

}
