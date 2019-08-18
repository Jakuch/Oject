package pl.sdacademy.sdafinalprojectrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.sdafinalprojectrest.model.dtos.TabDto;
import pl.sdacademy.sdafinalprojectrest.model.project.Project;
import pl.sdacademy.sdafinalprojectrest.model.project.Tab;
import pl.sdacademy.sdafinalprojectrest.service.TabService;

import java.util.List;

@RestController
@RequestMapping("/tabs")
public class TabController {

    @Autowired
    private TabService tabService;

    @GetMapping
    public List<Tab> getAll(){
        return tabService.getTabRepository().findAll();
    }

    @GetMapping("/project/{projectId}")
    public List<Tab> getTabsAssignedToProject(@PathVariable Long projectId){
        Project project = tabService.getProjectService().getProjectRepository()
                .findById(projectId)
                .orElseThrow(() -> new RuntimeException("No such project exists!"));
        return tabService.getTabRepository().findTabByProject(project);
    }

    @GetMapping("/{id}")
    public Tab getTabById(@PathVariable Long id){
        return tabService.getTabRepository().findById(id)
                .orElseThrow(() -> new RuntimeException("No such tab exists!"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tab createProjectTab(@RequestBody TabDto tabDto){
        return tabService.createTab(tabDto);
    }


    @PutMapping("/{id}")
    public Tab updateTab(@RequestBody TabDto tabDto, @PathVariable Long id){
        return tabService.updateTab(tabDto, id);
    }

    @DeleteMapping("/{id}")
    public Tab deleteTab(@PathVariable Long id){
        return tabService.deleteTab(id);
    }
}
