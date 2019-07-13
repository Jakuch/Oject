package pl.sdacademy.sdafinalprojectrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sdacademy.sdafinalprojectrest.model.project.Tab;
import pl.sdacademy.sdafinalprojectrest.model.project.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findTaskByTab(Tab tab);
}
