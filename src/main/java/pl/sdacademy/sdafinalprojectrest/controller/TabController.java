package pl.sdacademy.sdafinalprojectrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.sdafinalprojectrest.model.dtos.TabDto;
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

    @PostMapping
    public Tab createProjectTab(@RequestBody TabDto tabDto){
        return tabService.createTab(tabDto);
    }

}
