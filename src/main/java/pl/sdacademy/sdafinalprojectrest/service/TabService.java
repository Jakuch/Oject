package pl.sdacademy.sdafinalprojectrest.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdacademy.sdafinalprojectrest.model.dtos.TabDto;
import pl.sdacademy.sdafinalprojectrest.model.project.Project;
import pl.sdacademy.sdafinalprojectrest.model.project.Tab;
import pl.sdacademy.sdafinalprojectrest.repository.TabRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@NoArgsConstructor
public class TabService {

    @Autowired
    private TabRepository tabRepository;
    @Autowired
    private ProjectService projectService;

    public TabRepository getTabRepository() {
        return tabRepository;
    }

    public ProjectService getProjectService() {
        return projectService;
    }

    public List<Tab> showTabs(){
        //TODO now each project shows all the tabs, method should return list of tabs assigned to project
        return null;
    }

    public Tab createTab(TabDto tabDto){

        Tab tab = new Tab();
        tab.setTabName(tabDto.getTabName());
        tab.setTask(new ArrayList<>());
        projectService.getProjectRepository()
                .findById(tabDto.getProjectId())
                .ifPresent(project -> {
                    tab.setProject(project);
                    project.getTabList().add(tab);
                });

        tabRepository.save(tab);
        return tab;
    }

}
