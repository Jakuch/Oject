package pl.sdacademy.sdafinalprojectrest.model.project;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sdacademy.sdafinalprojectrest.model.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
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
//    private Integer storyPoints;
//    private LocalDateTime deadline;
    @OneToMany
    private List<User> contributors;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
