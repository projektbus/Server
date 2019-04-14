package ProjektBus.Server.repository;

import ProjektBus.Server.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    //TODO add embedded MongoDB
    @Test
    public void shouldSaveUser() {
        repository.deleteAll();
        User user = repository.save(new User("User1", "email@email.com", "haslo"));
        assertNull(user);
    }

}
