package ProjektBus.Server.resource;

import ProjektBus.Server.config.AppConfig;
import ProjektBus.Server.model.User;
import ProjektBus.Server.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableMongoRepositories({"ProjektBus.Server.repository"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class UserResourceTest {

    @Mock
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void findAll_TodosFound_ShouldReturnFoundTodoEntries() throws Exception {
        //Given
        User userFirst = new User("TestUser", "test@wp.pl", "hasloTest");

        //When
        when(userService.registerUser(any())).thenReturn(userFirst);

        //Expect
        mockMvc.perform(post("/user"))
                .andExpect(status().isCreated());
//                .andExpect(content().contentType(Json))
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].id", is(1)))
//                .andExpect(jsonPath("$[0].description", is("Lorem ipsum")))
//                .andExpect(jsonPath("$[0].title", is("Foo")))
//                .andExpect(jsonPath("$[1].id", is(2)))
//                .andExpect(jsonPath("$[1].description", is("Lorem ipsum")))
//                .andExpect(jsonPath("$[1].title", is("Bar")));

        verify(userService, times(1)).registerUser(any());
        verifyNoMoreInteractions(userService);
    }
}
