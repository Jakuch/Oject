package pl.sdacademy.sdafinalprojectrest.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdacademy.sdafinalprojectrest.model.dtos.ProjectDto;
import pl.sdacademy.sdafinalprojectrest.model.project.Project;
import pl.sdacademy.sdafinalprojectrest.model.user.User;
import pl.sdacademy.sdafinalprojectrest.repository.ProjectRepository;

import java.util.ArrayList;

@Service
@NoArgsConstructor
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    public ProjectRepository getProjectRepository() {
        return projectRepository;
    }

    public UserDetailsServiceImpl getUserDetailsService() {
        return userDetailsService;
    }

    public Project getSingleProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No such project exists"));
    }

    public Project createNewProject(ProjectDto projectDto) {

        String loggedUserName = userDetailsService.getLoggedUserName();
        User user = (User) userDetailsService.loadUserByUsername(loggedUserName);

        Project project = new Project();
        project.setTitle(projectDto.getTitle());
        project.setDescription(projectDto.getDescription());
        project.setAdmins(new ArrayList<>());
        project.setContributors(new ArrayList<>());
        project.setTabList(new ArrayList<>());
        project.addAdmin(user);

        return setProject(project, project);
    }

    public Project updateProject(ProjectDto projectDto, Long projectIdToUpdate) {
        Project projectToUpdate = getSingleProjectById(projectIdToUpdate);
        projectToUpdate.setTitle(projectDto.getTitle());
        projectToUpdate.setDescription(projectDto.getDescription());
        return projectToUpdate;
    }

    public void deleteProject(Long id) {
        projectRepository.findById(id)
                .ifPresent(project -> projectRepository.delete(project));
    }

    public Project setProject(Project projectToSet, Project projectToGet) {
        projectToSet.setTitle(projectToGet.getTitle());
        projectToSet.setDescription(projectToGet.getDescription());
        projectToSet.setTabList(projectToGet.getTabList());
        projectToSet.setAdmins(projectToGet.getAdmins());
        projectToSet.setContributors(projectToGet.getContributors());
        projectRepository.save(projectToSet);
        return projectToSet;
    }

}
