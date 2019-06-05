package pl.sdacademy.sdafinalprojectrest.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    public Project getSingleProjectById(Long id){
        Project userProject = new Project();

        projectRepository.findById(id)
                .ifPresent(project -> {
                    boolean contains = project.getContributors()
                            .contains(getLoggedUser());
                    if (contains) {
                        setProject(userProject, project);
                    } else {
                        throw new RuntimeException("No such project!");
                    }
                });
        return userProject;
    }

    public Project createNewProject(ProjectDto projectDto){

        String username = getLoggedUsersName();

        User user = (User) userDetailsService.loadUserByUsername(username);

        Project project = new Project();
        project.setTitle(projectDto.getTitle());
        project.setDescription(projectDto.getDescription());
        project.setProjectTabList(new ArrayList<>());
        project.setAdmins(new ArrayList<>());
        project.setContributors(new ArrayList<>());

        project.addAdmin(user);
        project.addContributor(user);
        projectRepository.save(project);

        return project;
    }

    public void updateProject(ProjectDto projectDto, Long id){
        Project projectToUpdate = getSingleProjectById(id);
        projectToUpdate.setTitle(projectDto.getTitle());
        projectToUpdate.setDescription(projectDto.getDescription());
    }

    public void deleteProject(Long id){
        projectRepository.findById(id)
                .ifPresent(project -> projectRepository.delete(project));
    }

    public String getLoggedUsersName() {
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    public User getLoggedUser() {
        String username = getLoggedUsersName();
        return (User) userDetailsService.loadUserByUsername(username);
    }

    public Project setProject(Project projectToSet, Project projectToGet) {
        projectToSet.setTitle(projectToGet.getTitle());
        projectToSet.setDescription(projectToGet.getDescription());
        projectToSet.setProjectTabList(projectToGet.getProjectTabList());
        projectToSet.setAdmins(projectToGet.getAdmins());
        projectToSet.setContributors(projectToGet.getContributors());
        return projectToSet;
    }
}
