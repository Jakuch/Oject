package pl.sdacademy.sdafinalprojectrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.sdafinalprojectrest.model.dtos.ProjectDto;
import pl.sdacademy.sdafinalprojectrest.model.project.Project;
import pl.sdacademy.sdafinalprojectrest.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getProjectRepository()
                .findProjectsByContributors(projectService.getUserDetailsService().getLoggedUser());

    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectService.getSingleProjectById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project createProject(@RequestBody ProjectDto projectDto) {

        return projectService.createNewProject(projectDto);
    }

    @PutMapping("/{id}")
    public void editProject(@RequestBody ProjectDto projectDto, @PathVariable Long id) {
        projectService.updateProject(projectDto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
}
