package ProjektBus.Server.service;

import ProjektBus.Server.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void shouldSaveUser() {
        User user = new User("UserTest", "email@email.com", "haslo");
        userService.registerUser(user);
        assertNotNull(user.getId());
    }
}
