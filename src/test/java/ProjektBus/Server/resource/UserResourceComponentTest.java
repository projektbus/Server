package ProjektBus.Server.resource;

import ProjektBus.Server.Application;
import ProjektBus.Server.model.User;
import ProjektBus.Server.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {Application.class})
public class UserResourceComponentTest {

    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Before
    public void setUp(){
        userRepository.deleteAll();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void should_findAllUsers() throws Exception {
        User user = new User("testLogin", "test@wp.pl", "testPassword");
        User user2 = new User("testLogin2", "test2@wp.pl", "testPassword2");
        userRepository.save(user);
        userRepository.save(user2);

        mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].login").value("testLogin"))
                .andExpect(jsonPath("$[0].email").value("test@wp.pl"))
                .andExpect(jsonPath("$[0].password").value("testPassword"))
                .andExpect(jsonPath("$[1].login").value("testLogin2"))
                .andExpect(jsonPath("$[1].email").value("test2@wp.pl"))
                .andExpect(jsonPath("$[1].password").value("testPassword2"));
    }

    @Test
    public void should_findUser() throws Exception {
        User user = new User("testLogin", "test@wp.pl", "testPassword");
        userRepository.save(user);

        mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].login").value("testLogin"))
                .andExpect(jsonPath("$[0].email").value("test@wp.pl"))
                .andExpect(jsonPath("$[0].password").value("testPassword"));
    }

    @Test
    public void should_SaveUser() throws Exception {
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{" +
                        "\"login\" : \"testLogin\"," +
                        "\"email\" : \"testEmail@wp.pl\"," +
                        "\"password\" : \"TestPassword1.\"" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", equalTo("https://peaceful-sierra-14544.herokuapp.com/users/testLogin")));
    }

//    @Test
//    public void should_ThrowUserValidatorException() throws Exception {
//        mockMvc.perform(post("/users")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content("{" +
//                        "\"login\" : \"tesasdt\"," +
//                        "\"email\" : \"testEmail.pl\"," +
//                        "\"password\" : \"\"" +
//                        "}")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.errors[0]").value("password: Size must be between 5 and 32 letters"))
//                .andExpect(jsonPath("$.errors[1]").value("user: Wrong email address"))
//                .andExpect(jsonPath("$.errors[2]").value("user: Password must contain letters"))
//                .andExpect(jsonPath("$.errors[3]").value("user: Password must contain numbers"))
//                .andExpect(jsonPath("$.errors[4]").value("user: Password must contain special characters"));
//    }
//
//    @Test
//    public void saveUserWithExistingLoginAdnEmail_should_ThrowUserValidationsException() throws Exception {
//        mockMvc.perform(post("/users")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content("{" +
//                        "\"login\" : \"testLogin\"," +
//                        "\"email\" : \"testEmail@wp.pl\"," +
//                        "\"password\" : \"testPassword1.\"" +
//                        "}")
//                .accept(MediaType.APPLICATION_JSON));
//
//        //Save User with the same login and email
//        mockMvc.perform(post("/users")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content("{" +
//                        "\"login\" : \"testLogin\"," +
//                        "\"email\" : \"testEmail@wp.pl\"," +
//                        "\"password\" : \"testPassword1.\"" +
//                        "}")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.errors[0]").value("login: Login exists"))
//                .andExpect(jsonPath("$.errors[1]").value("email: Email exists"));
//    }
}
