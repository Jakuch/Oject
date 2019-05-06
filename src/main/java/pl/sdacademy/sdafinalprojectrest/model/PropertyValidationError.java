package pl.sdacademy.sdafinalprojectrest.model;

import lombok.Data;

@Data
public class PropertyValidationError {

    private String property;
    private String message;

    public PropertyValidationError(String property, String message) {
        this.property = property;
        this.message = message;
    }
}
