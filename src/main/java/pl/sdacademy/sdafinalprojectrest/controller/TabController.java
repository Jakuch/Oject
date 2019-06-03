package pl.sdacademy.sdafinalprojectrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sdacademy.sdafinalprojectrest.model.dtos.TabDto;
import pl.sdacademy.sdafinalprojectrest.model.project.ProjectTab;
import pl.sdacademy.sdafinalprojectrest.repository.ProjectRepository;
import pl.sdacademy.sdafinalprojectrest.repository.ProjectTabRepository;
import pl.sdacademy.sdafinalprojectrest.service.UserDetailsServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class TabController {

    private ProjectTabRepository projectTabRepository;
    private ProjectRepository projectRepository;
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public TabController(ProjectTabRepository projectTabRepository,
                         ProjectRepository projectRepository,
                         UserDetailsServiceImpl userDetailsService) {
        this.projectTabRepository = projectTabRepository;
        this.projectRepository = projectRepository;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    public List<ProjectTab> getAll(){
        return projectTabRepository.findAll();
    }

    @PostMapping
    public ProjectTab createProjectTab(TabDto tabDto){
        return null;
    }
}
