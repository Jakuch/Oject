package pl.sdacademy.sdafinalprojectrest.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TaskDto {
    private Long tabId;
    private String title;
    private String description;
    private Integer storyPoints;
    private LocalDateTime dueDate;
}
