package pl.sdacademy.sdafinalprojectrest.model.project;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@ToString(exclude = {"project"})
public class Tab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tabName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
    @OneToMany(mappedBy = "tab",
            cascade = CascadeType.ALL)
    private List<Task> task;

    public Tab(String tabName, Project project, List<Task> task) {
        this.tabName = tabName;
        this.project = project;
        this.task = task;
    }
}
