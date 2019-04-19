package ProjektBus.Server.repository;

import ProjektBus.Server.model.User;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertNotNull;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

   @Test
    public void shouldSaveUser() {
        repository.deleteAll();
        User user = repository.save(new User("User1a", "email@email.com", "haslo"));
        assertNotNull(user);
        assertNotNull(user.getId());
    }

}
