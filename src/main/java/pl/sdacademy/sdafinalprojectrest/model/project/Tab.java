package pl.sdacademy.sdafinalprojectrest.model.project;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Tab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tabName;
    @OneToMany
    private List<Task> task;

    public Tab(String tabName, List<Task> task) {
        this.tabName = tabName;
        this.task = task;
    }
}
