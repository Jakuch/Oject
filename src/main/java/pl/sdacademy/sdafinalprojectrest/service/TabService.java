package pl.sdacademy.sdafinalprojectrest.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdacademy.sdafinalprojectrest.model.dtos.TabDto;
import pl.sdacademy.sdafinalprojectrest.model.project.ProjectTab;
import pl.sdacademy.sdafinalprojectrest.model.user.User;
import pl.sdacademy.sdafinalprojectrest.repository.ProjectTabRepository;

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

    public ProjectTab createTab(TabDto tabDto){
        User loggedUser = projectService.getUserDetailsService().getLoggedUser();
        return null; //TODO
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
