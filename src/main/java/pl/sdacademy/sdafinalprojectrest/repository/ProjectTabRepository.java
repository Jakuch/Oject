package pl.sdacademy.sdafinalprojectrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sdacademy.sdafinalprojectrest.model.project.ProjectTab;

import java.util.List;

@Repository
public interface ProjectTabRepository extends JpaRepository<ProjectTab, Long> {
}
