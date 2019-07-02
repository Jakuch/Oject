package pl.sdacademy.sdafinalprojectrest.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdacademy.sdafinalprojectrest.model.dtos.TabDto;
import pl.sdacademy.sdafinalprojectrest.model.project.Project;
import pl.sdacademy.sdafinalprojectrest.model.project.Tab;
import pl.sdacademy.sdafinalprojectrest.repository.TabRepository;

import java.util.ArrayList;
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

    public Tab createTab(TabDto tabDto) {
//TODO not working properly, project field is null
        Tab tab = new Tab();
        tab.setTabName(tabDto.getTabName());
        tab.setTask(new ArrayList<>());
        Optional<Project> foundProject = projectService.getProjectRepository()
                .findById(tabDto.getProjectId());
        foundProject.ifPresent(project -> project.createTab(tab));
        tabRepository.save(tab);
        return tab;
    }

}
