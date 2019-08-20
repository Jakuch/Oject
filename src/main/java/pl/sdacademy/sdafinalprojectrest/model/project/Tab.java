package pl.sdacademy.sdafinalprojectrest.model.project;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Tab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tabName;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
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
