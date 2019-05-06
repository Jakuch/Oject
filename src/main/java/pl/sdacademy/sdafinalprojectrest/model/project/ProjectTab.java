package pl.sdacademy.sdafinalprojectrest.model.project;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class ProjectTab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tabName;
    @OneToMany
    private List<ProjectTask> projectTask;
}
