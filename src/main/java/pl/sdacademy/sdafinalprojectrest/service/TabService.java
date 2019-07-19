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

        Project foundProject = projectService.getProjectRepository()
                .findById(tabDto.getProjectId())
                .orElseThrow(() -> new RuntimeException("No such project exists"));

        Tab tab = new Tab(tabDto.getTabName(), foundProject, new ArrayList<>());

        tabRepository.save(tab);
        return tab;
    }

    public Tab updateTab(TabDto tabDto, Long id) {
        Tab tab = tabRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No such tab exists!"));
        Project project = projectService.getProjectRepository().findById(tabDto.getProjectId())
                .orElseThrow(() -> new RuntimeException("No such project exists!"));

        tab.setTabName(tabDto.getTabName());
        tab.setProject(project);
        return tabRepository.save(tab);
    }

    public Tab deleteTab(Long id) {
        Tab tab = tabRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No such tab exists!"));
        tab.setProject(null);
        tabRepository.delete(tab);
        return tab;
    }
}
