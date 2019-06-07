package pl.sdacademy.sdafinalprojectrest.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectDto {
    private String title;
    private String description;

    public ProjectDto(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
