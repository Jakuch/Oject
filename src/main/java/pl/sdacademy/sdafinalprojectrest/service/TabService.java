package pl.sdacademy.sdafinalprojectrest.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public Tab createTab(String tabName, Long projectId){

        Tab tab = new Tab();
        tab.setTabName(tabName);
        tab.setTask(new ArrayList<>());
        Optional<Project> projectById = projectService.getProjectRepository().findById(projectId);
        projectById.ifPresent(project -> project.getTabList().add(tab));
        tabRepository.save(tab);
        return tab;
    }

    public void setTabRepository(TabRepository tabRepository) {
        this.tabRepository = tabRepository;
    }

    public ProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }
}
