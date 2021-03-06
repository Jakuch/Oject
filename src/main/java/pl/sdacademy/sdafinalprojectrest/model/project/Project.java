package pl.sdacademy.sdafinalprojectrest.model.project;

import lombok.*;
import pl.sdacademy.sdafinalprojectrest.model.user.User;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @OneToMany(mappedBy = "project",
            cascade = CascadeType.ALL)
    private List<Tab> tabList;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private List<User> admins;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private List<User> contributors;

    public Project(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Project(String title, String description, List<Tab> tabList, List<User> admins, List<User> contributors) {
        this.title = title;
        this.description = description;
        this.tabList = tabList;
        this.admins = admins;
        this.contributors = contributors;
    }

    public void addAdmin(User user) {
        user.getAdministratedProjects().add(this);
        user.getContributions().add(this);
        admins.add(user);
        contributors.add(user);
    }

    public void addContributor(User user) {
        user.getContributions().add(this);
        contributors.add(user);
    }

    public List<User> getContributorsById(List<Long> ids){
        List<Long> userIds = contributors.stream()
                .map(User::getId)
                .filter(ids::contains)
                .collect(Collectors.toList());

        return contributors.stream()
                .filter(user -> userIds.contains(user.getId()))
                .collect(Collectors.toList());
    }
}
