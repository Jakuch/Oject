package pl.sdacademy.sdafinalprojectrest.model.project;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sdacademy.sdafinalprojectrest.model.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @OneToMany
    private List<ProjectTab> projectTabList;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private List<User> admins;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private List<User> contributors;

    public void addAdmin(User user){
        admins.add(user);
    }

    public void addContributor(User user){
        contributors.add(user);
    }

    public void createTab(ProjectTab tab) {
        projectTabList.add(tab);
    }
}
