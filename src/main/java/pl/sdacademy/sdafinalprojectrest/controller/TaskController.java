package pl.sdacademy.sdafinalprojectrest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.sdafinalprojectrest.model.dtos.TaskDto;
import pl.sdacademy.sdafinalprojectrest.model.project.Tab;
import pl.sdacademy.sdafinalprojectrest.model.project.Task;
import pl.sdacademy.sdafinalprojectrest.model.user.User;
import pl.sdacademy.sdafinalprojectrest.service.TaskService;


import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAll(){
        return taskService.getTaskRepository().findAll();
    }

    @GetMapping("/{id}")
    public Task getSingleTask(@PathVariable Long id){
        return taskService.getTaskRepository().findById(id)
                .orElseThrow(()-> new RuntimeException("No such task exists!"));
    }

    @GetMapping("/tab/{tabId}")
    public List<Task> getTaskAssignedToTab(@PathVariable Long tabId){
        Tab tab = taskService.getTabService().getTabRepository()
                .findById(tabId)
                .orElseThrow(() -> new RuntimeException("No such tab exists!"));
        return taskService.getTaskRepository().findTaskByTab(tab);
    }

    @PostMapping
    public Task addTask(@RequestBody TaskDto taskDto){
        return taskService.createTask(taskDto);
    }

    @PutMapping
    public Task updateTask(@RequestBody TaskDto taskDto){
        return null;
    }

    @PutMapping("/updateStatus/{id}")
    public Task setTaskStatusToFinished(@PathVariable Long id){
        return taskService.updateTaskStatusById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        Task task = taskService.getTaskRepository().findById(id)
                .orElseThrow(() -> new RuntimeException("No such task exists!"));
        taskService.getTaskRepository().delete(task);
    }


}
