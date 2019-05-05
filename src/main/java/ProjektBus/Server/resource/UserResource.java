package ProjektBus.Server.resource;

import ProjektBus.Server.model.User;
import ProjektBus.Server.repository.UserRepository;
import ProjektBus.Server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserResource {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    public ResponseEntity saveUser(@RequestBody User user) {

        userService.registerUser(user);
        return new ResponseEntity(HttpStatus.CREATED);

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

    @GetMapping("/user")
    public @ResponseBody ResponseEntity updatePassword(@RequestBody User user, @RequestParam("password")String password){
        userService.updatePassword(user, password);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity deleteUser(@RequestBody User user){
        userService.deleteUser(user);
        return new ResponseEntity(HttpStatus.OK);
    }

}
