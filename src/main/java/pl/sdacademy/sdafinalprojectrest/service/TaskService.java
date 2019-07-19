package pl.sdacademy.sdafinalprojectrest.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdacademy.sdafinalprojectrest.model.dtos.TaskDto;
import pl.sdacademy.sdafinalprojectrest.model.project.Tab;
import pl.sdacademy.sdafinalprojectrest.model.project.Task;
import pl.sdacademy.sdafinalprojectrest.repository.TaskRepository;

import java.util.ArrayList;

@Service
@NoArgsConstructor
public class TaskService {

    @Autowired
    private TabService tabService;
    @Autowired
    private TaskRepository taskRepository;

    public TabService getTabService() {
        return tabService;
    }

    public TaskRepository getTaskRepository() {
        return taskRepository;
    }

    public Task createTask(TaskDto taskDto){
        Tab foundTab = tabService.getTabRepository().findById(taskDto.getTabId())
                .orElseThrow(() -> new RuntimeException("No such tab exists!"));

        Task task = new Task();
        task.setTab(foundTab);
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStoryPoints(taskDto.getStoryPoints());
        task.setDueDate(taskDto.getDueDate());
        task.setContributors(new ArrayList<>());

        return taskRepository.save(task);
    }

}
