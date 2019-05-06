package pl.sdacademy.sdafinalprojectrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.sdafinalprojectrest.model.dtos.ProjectDto;
import pl.sdacademy.sdafinalprojectrest.model.project.Project;
import pl.sdacademy.sdafinalprojectrest.model.user.User;
import pl.sdacademy.sdafinalprojectrest.repository.ProjectRepository;
import pl.sdacademy.sdafinalprojectrest.repository.UserRepository;
import pl.sdacademy.sdafinalprojectrest.service.UserDetailsServiceImpl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {


    private ProjectRepository projectRepository;
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public ProjectController(ProjectRepository projectRepository, UserDetailsServiceImpl userDetailsService) {
        this.projectRepository = projectRepository;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
    
    @GetMapping("{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("No such project!"));
    }
    
    @PostMapping
    public Project createProject(@RequestBody ProjectDto projectDto) {

        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String username;

        if(principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

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
    
    @PutMapping("{id}")
    public void editProject(@RequestBody ProjectDto projectDto, @PathVariable Long id) {
        Project projectToUpdate = getProjectById(id);
        projectToUpdate.setTitle(projectDto.getTitle());
        projectToUpdate.setDescription(projectDto.getDescription());
    }

    @DeleteMapping("{id}")
    public void deleteProject(@PathVariable Long id) {
       projectRepository.findById(id)
               .ifPresent(project -> projectRepository.delete(project));
    }
}
