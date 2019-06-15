package pl.sdacademy.sdafinalprojectrest;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sdacademy.sdafinalprojectrest.repository.ProjectRepository;
import pl.sdacademy.sdafinalprojectrest.repository.TabRepository;
import pl.sdacademy.sdafinalprojectrest.repository.UserRepository;
import pl.sdacademy.sdafinalprojectrest.service.ProjectService;
import pl.sdacademy.sdafinalprojectrest.service.TabService;

@RunWith(SpringRunner.class)
public class TabServiceTest {

    static class TabServiceTestConfiguration {

        @Bean
        public TabService tabService() {
            return new TabService();
        }

        @Bean
        public ProjectService projectService(){
            return new ProjectService();
        }
    }

    @Autowired
    private TabService tabService;
    @Autowired
    private ProjectService projectService;

    @MockBean
    private TabRepository tabRepository;
    @MockBean
    private ProjectRepository projectRepository;
    @MockBean
    private UserRepository userRepository;



}
