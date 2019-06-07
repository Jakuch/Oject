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
import pl.sdacademy.sdafinalprojectrest.service.ProjectService;
import pl.sdacademy.sdafinalprojectrest.service.TabService;
import pl.sdacademy.sdafinalprojectrest.service.UserDetailsServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/tabs")
public class TabController {

    @Autowired
    private TabService tabService;

    @GetMapping
    public List<ProjectTab> getAll(){
        return tabService.getTabRepository().findAll();
    }

    @PostMapping
    public ProjectTab createProjectTab(TabDto tabDto){

        return null;
    }
}
