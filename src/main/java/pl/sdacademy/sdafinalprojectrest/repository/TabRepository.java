package pl.sdacademy.sdafinalprojectrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sdacademy.sdafinalprojectrest.model.project.Project;
import pl.sdacademy.sdafinalprojectrest.model.project.Tab;

import java.util.List;

@Repository
public interface TabRepository extends JpaRepository<Tab, Long> {

    Tab findByTabName(String tabName);
    List<Tab> findTabByProject(Project project);
}
