package pl.sdacademy.sdafinalprojectrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sdacademy.sdafinalprojectrest.model.project.Project;
import pl.sdacademy.sdafinalprojectrest.model.user.User;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findProjectsByContributors(User user);
}
