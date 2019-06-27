package pl.sdacademy.sdafinalprojectrest.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdacademy.sdafinalprojectrest.model.dtos.TabDto;
import pl.sdacademy.sdafinalprojectrest.model.project.Tab;
import pl.sdacademy.sdafinalprojectrest.repository.TabRepository;

import java.util.ArrayList;

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

        Tab tab = new Tab();
        tab.setTabName(tabDto.getTabName());
        tab.setTask(new ArrayList<>());
        projectService.getProjectRepository()
                .findById(tabDto.getProjectId())
                .ifPresent(tab::setProject);

        tabRepository.save(tab);
        return tab;
    }

}
