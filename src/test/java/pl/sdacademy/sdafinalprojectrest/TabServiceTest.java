package pl.sdacademy.sdafinalprojectrest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sdacademy.sdafinalprojectrest.model.dtos.TabDto;
import pl.sdacademy.sdafinalprojectrest.model.project.Project;
import pl.sdacademy.sdafinalprojectrest.repository.ProjectRepository;
import pl.sdacademy.sdafinalprojectrest.repository.TabRepository;
import pl.sdacademy.sdafinalprojectrest.repository.UserRepository;
import pl.sdacademy.sdafinalprojectrest.service.ProjectService;
import pl.sdacademy.sdafinalprojectrest.service.TabService;
import pl.sdacademy.sdafinalprojectrest.service.UserDetailsServiceImpl;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class TabServiceTest {

    @TestConfiguration
    static class TabServiceTestConfiguration {

        @Bean
        public TabService tabService() {
            return new TabService();
        }

        @Bean
        public ProjectService projectService(){
            return new ProjectService();
        }

        @Bean
        public UserDetailsServiceImpl userDetailsService(){ return new UserDetailsServiceImpl();}
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

    @Test
    public void testShowTabs(){
        //TODO test for showTabs method in TabService class
    }

    @Test
    public void testCreateTab(){
        TabDto tabDto = new TabDto();
        tabDto.setTabName("TestTab");
        tabDto.setProjectId(1L);
        tabService.createTab(tabDto);

        assertEquals(tabRepository.findByTabName("TestTab").getTabName(), tabDto.getTabName());

        Project project = projectRepository.findById(1L).get();
        assertEquals(project.getId(), tabDto.getProjectId());
    }

}
