package pl.sdacademy.sdafinalprojectrest;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sdacademy.sdafinalprojectrest.model.user.User;
import pl.sdacademy.sdafinalprojectrest.repository.ProjectRepository;
import pl.sdacademy.sdafinalprojectrest.repository.UserRepository;
import pl.sdacademy.sdafinalprojectrest.service.ProjectService;
import pl.sdacademy.sdafinalprojectrest.service.UserDetailsServiceImpl;

@RunWith(SpringRunner.class)
public class ProjectServiceTest {

    @TestConfiguration
    static class ProjectServiceTestConfiguration {

        @Bean
        public ProjectService projectService(){
            return new ProjectService();
        }

        @Bean
        public UserDetailsServiceImpl userDetailsService(){
            return new UserDetailsServiceImpl();
        }
    }

    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @MockBean
    private ProjectRepository projectRepository;
    @MockBean
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        User testUser = new User("TestUser", "testuser", "test@test.com", User.Role.USER);

        Mockito.when(userRepository.findByUsername(testUser.getUsername())).getMock();
    }
}
