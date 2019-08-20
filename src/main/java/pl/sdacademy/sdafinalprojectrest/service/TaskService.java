package pl.sdacademy.sdafinalprojectrest.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdacademy.sdafinalprojectrest.model.dtos.TaskDto;
import pl.sdacademy.sdafinalprojectrest.model.project.Project;
import pl.sdacademy.sdafinalprojectrest.model.project.Tab;
import pl.sdacademy.sdafinalprojectrest.model.project.Task;
import pl.sdacademy.sdafinalprojectrest.model.user.User;
import pl.sdacademy.sdafinalprojectrest.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public Task createTask(TaskDto taskDto) {
        Task task = new Task();
        return setTask(task, taskDto);
    }

    public Task updateTaskFromDto(TaskDto taskDto, Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("No such task exists!"));
        return setTask(task, taskDto);
    }

    private Task setTask(Task task, TaskDto taskDto) {
        Tab foundTab = tabService.getTabRepository()
                .findById(taskDto.getTabId())
                .orElseThrow(() -> new RuntimeException("No such tab exists!"));
        Project project = tabService.getProjectService().getProjectRepository()
                .findById(foundTab.getProject().getId())
                .orElseThrow(() -> new RuntimeException("No such project exists!"));

        List<Long> projectContributorIds = project.getContributors().stream()
                .map(User::getId)
                .filter(id -> taskDto.getContributors().contains(id))
                .collect(Collectors.toList());


        task.setTab(foundTab);
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStoryPoints(taskDto.getStoryPoints());
        task.setDueDate(taskDto.getDueDate());
        task.setContributors(project.getContributorsById(projectContributorIds));
        return taskRepository.save(task);
    }
}
