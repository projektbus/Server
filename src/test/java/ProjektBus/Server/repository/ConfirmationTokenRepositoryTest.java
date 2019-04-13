package ProjektBus.Server.repository;

import ProjektBus.Server.model.ConfirmationToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfirmationTokenRepositoryTest {

    @Autowired
    private ConfirmationTokenRepository repository;

    @Test
    public void shouldSaveConfirmationToken() {
        repository.deleteAll();
        ConfirmationToken confirmationToken = repository.save(new ConfirmationToken("userId"));
        assertNotNull(confirmationToken);
    }

}