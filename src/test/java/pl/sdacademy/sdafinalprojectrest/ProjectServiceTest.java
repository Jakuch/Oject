package pl.sdacademy.sdafinalprojectrest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sdacademy.sdafinalprojectrest.model.dtos.ProjectDto;
import pl.sdacademy.sdafinalprojectrest.model.project.Project;
import pl.sdacademy.sdafinalprojectrest.model.user.User;
import pl.sdacademy.sdafinalprojectrest.repository.ProjectRepository;
import pl.sdacademy.sdafinalprojectrest.repository.UserRepository;
import pl.sdacademy.sdafinalprojectrest.service.ProjectService;
import pl.sdacademy.sdafinalprojectrest.service.UserDetailsServiceImpl;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
public class ProjectServiceTest {

    @TestConfiguration
    static class ProjectServiceTestConfiguration {

        @Bean
        public ProjectService projectService() {
            return new ProjectService();
        }

        @Bean
        public UserDetailsServiceImpl userDetailsService() {
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
        User testUser = new User("TestUser",
                "testuser",
                "test@test.com",
                User.Role.USER,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>());

        Project testProject = new Project("TestProject",
                "TestDescription",
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>());

        testProject.addAdmin(testUser);

        Mockito.when(userRepository.findUserByUsername(testUser.getUsername())).thenReturn(testUser);
        Mockito.when(projectRepository.findProjectByTitle(testProject.getTitle())).thenReturn(testProject);
    }

    @Test
    public void shouldReturnProjectRepository(){
        ProjectRepository found = projectService.getProjectRepository();
        assertEquals(found, projectRepository);
    }

    @Test
    public void shouldReturnUserDetailsService(){
        UserDetailsServiceImpl found = projectService.getUserDetailsService();
        assertEquals(found, userDetailsService);
    }

    @Test
    public void whenValidTitle_shouldFindProject() {
        String title = "TestProject";
        Project foundProject = projectService.getProjectRepository().findProjectByTitle(title);

        assertEquals(foundProject.getTitle(), title);

    }

    @Test
    public void testGetSingleProjectById() {
        long id = 1;
        Project singleProjectById = projectService.getSingleProjectById(id);

        assertEquals(singleProjectById, new Project());
    }

    @Test
    public void testUpdateProject(){
        ProjectDto projectDto = new ProjectDto("ProjectDtoTitle", "ProjectDtoDescription");
        Project updatedProject = projectService.updateProject(projectDto, 1L);

        assertEquals(updatedProject.getTitle(), projectDto.getTitle());
        assertEquals(updatedProject.getDescription(), projectDto.getDescription());
    }

    @Test
    public void testSetProject(){
        Project projectToGet = new Project("First", "Description",
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Project projectToSet = new Project("Second", "Description");

        assertNotEquals(projectToSet, projectToGet);

        Project project = projectService.setProject(projectToSet, projectToGet);

        assertEquals(project, projectToGet);
    }
}
