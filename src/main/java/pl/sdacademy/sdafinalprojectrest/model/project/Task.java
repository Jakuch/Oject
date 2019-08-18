package pl.sdacademy.sdafinalprojectrest.model.project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sdacademy.sdafinalprojectrest.model.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tab_id")
    private Tab tab;
    private Integer storyPoints;
    private LocalDate dueDate;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private List<User> contributors;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
