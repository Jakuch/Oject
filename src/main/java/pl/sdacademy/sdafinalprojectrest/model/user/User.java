package pl.sdacademy.sdafinalprojectrest.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.sdacademy.sdafinalprojectrest.model.project.Project;
import pl.sdacademy.sdafinalprojectrest.model.project.Task;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull

    private String username;
    @NotNull
    @Length(min = 8)
    private String password;
    @NotNull
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    @JsonIgnore
    @ManyToMany(mappedBy = "admins")
    private List<Project> administratedProjects;
    @JsonIgnore
    @ManyToMany(mappedBy = "contributors")
    private List<Project> contributions;
    @JsonIgnore
    @ManyToMany(mappedBy = "contributors")
    private List<Task> tasks;

    public User(String username, String password, String email, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User(@NotNull String username,
                @NotNull @Length(min = 8) String password,
                @NotNull String email, Role role,
                List<Project> administratedProjects,
                List<Project> contributions,
                List<Task> tasks) {

        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.administratedProjects = administratedProjects;
        this.contributions = contributions;
        this.tasks = tasks;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public enum Role {
        USER,
        ADMIN,
    }
}
