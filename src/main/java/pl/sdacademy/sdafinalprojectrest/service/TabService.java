package pl.sdacademy.sdafinalprojectrest.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdacademy.sdafinalprojectrest.model.project.Project;
import pl.sdacademy.sdafinalprojectrest.model.project.ProjectTab;
import pl.sdacademy.sdafinalprojectrest.repository.ProjectTabRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
@NoArgsConstructor
public class TabService {

    @Autowired
    private ProjectTabRepository tabRepository;
    @Autowired
    private ProjectService projectService;


    public ProjectTabRepository getTabRepository() {
        return tabRepository;
    }

    public ProjectTab createTab(String tabName, Long projectId){

        ProjectTab projectTab = new ProjectTab();
        projectTab.setTabName(tabName);
        projectTab.setTabTask(new ArrayList<>());
        Optional<Project> projectById = projectService.getProjectRepository().findById(projectId);
        projectById.ifPresent(project -> project.getProjectTabList().add(projectTab));
        tabRepository.save(projectTab);
        return projectTab;
    }

    public void setTabRepository(ProjectTabRepository tabRepository) {
        this.tabRepository = tabRepository;
    }

    public ProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }
}
