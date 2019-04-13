package ProjektBus.Server;

import ProjektBus.Server.model.User;
import ProjektBus.Server.repository.UserRepository;
import ProjektBus.Server.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmbeddedMongoTest{

    @Autowired
    private UserService userService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create_should_create_new_student() {
        User user = new User("a", "b", "c");

        userService.registerUser(user);
        List<User> studs = userRepository.findAll();
        assertTrue(studs.size() == 1);
        assertEquals("a", studs.get(0).getLogin());
    }
}