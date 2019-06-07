package pl.sdacademy.sdafinalprojectrest.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import pl.sdacademy.sdafinalprojectrest.model.project.Project;
import pl.sdacademy.sdafinalprojectrest.model.user.User;
import pl.sdacademy.sdafinalprojectrest.repository.UserRepository;

@Service
@NoArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public String getLoggedUsersName() {
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    public User getLoggedUser() {
        String username = getLoggedUsersName();
        return userRepository.findUserByUsername(username);
    }

    public void addAdministratedProjectToUser(String username, Project project){
        userRepository.findUserByUsername(username).getAdministratedProjects().add(project);
        userRepository.findUserByUsername(username).getContributions().add(project);
    }

    public void addAdministratedProjectToUser(Project project){
        getLoggedUser().getAdministratedProjects().add(project);
        getLoggedUser().getContributions().add(project);
    }

    public void addContributorToProject(Project project){
        getLoggedUser().getContributions().add(project);
    }
    public void addContributorToProject(String username, Project project){
        userRepository.findUserByUsername(username).getContributions().add(project);
    }
}
