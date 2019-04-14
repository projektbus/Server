package ProjektBus.Server.repository;

import ProjektBus.Server.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

   @Test
    public void shouldSaveUser() {
        repository.deleteAll();
        User user = repository.save(new User("User1a", "email@email.com", "haslo"));
        assertNotNull(user);
    }

}
