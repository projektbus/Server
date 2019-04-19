//package ProjektBus.Server.repository;
//
//import ProjektBus.Server.model.User;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import static org.junit.Assert.assertNotNull;
//
//@DataMongoTest
//@ExtendWith(SpringExtension.class)
//public class UserRepositoryTest {
//
//    private SampleRepositoryMongoImpl sampleRepositoryMongo;
//
//
//    @Before
//    public void setUp() throws Exception {
//        sampleRepositoryMongo = new SampleRepositoryMongoImpl();
//    }
//
//   @Test
//    public void shouldSaveUser() {
//        sampleRepositoryMongo.deleteAll();
//        User user = sampleRepositoryMongo.save(new User("User1a", "email@email.com", "haslo"));
//        assertNotNull(user);
//        assertNotNull(user.getId());
//    }
//
//}
