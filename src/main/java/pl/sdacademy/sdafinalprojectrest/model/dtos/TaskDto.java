package pl.sdacademy.sdafinalprojectrest.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sdacademy.sdafinalprojectrest.model.user.User;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class TaskDto {
    private Long tabId;
    private String title;
    private String description;
    private Integer storyPoints;
    private LocalDate dueDate;
    private List<Long> contributors;
}
