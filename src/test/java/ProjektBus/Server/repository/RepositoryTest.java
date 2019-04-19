package ProjektBus.Server.repository;

import ProjektBus.Server.Application;
import ProjektBus.Server.model.User;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= {Application.class})
public class RepositoryTest {

    @Autowired
    private UserRepository transactionRepository;

    @Autowired

    private MongoTemplate mongoTemplate;

    private final static List<String> USER_ID_LIST = Arrays.asList("b2b1f340-cba2-11e8-ad5d-873445c542a2", "bd5dd3a4-cba2-11e8-9594-3356a2e7ef10");

    private static final Random RANDOM = new Random();


    @BeforeEach
    public void dataSetup() {
        User transaction;
        for (int i = 0; i < 10; i++) {
            String requestId = UUID.randomUUID().toString();
            if (i % 2 == 0) {
                transaction = new User("a", "b", "c");
            } else {
                transaction = new User("a", "b", "c");
            }

            transactionRepository.save(transaction);
            System.out.println();
        }
    }


    @Test
    public void findSuccessfullOperationsForUserWithCreatedDateLessThanNowTest() {
        long now = System.currentTimeMillis();
        String userId = USER_ID_LIST.get(RANDOM.nextInt(2));
        List<User> resultsPage =  transactionRepository.findAll();
        System.out.println();
//        assertThat("a").isNotEmpty();
//        assertThat(resultsPage).extracting("userId").allMatch(id -> Objects.equals(id, userId));
//        assertThat(resultsPage).extracting("created").isSortedAccordingTo(Collections.reverseOrder());
//        assertThat(resultsPage).extracting("created").first().matches(createdTimeStamp -> (Long)createdTimeStamp <= now);
//        assertThat(resultsPage).extracting("success").allMatch(sucessfull -> (Boolean)sucessfull == true);
    }
}
