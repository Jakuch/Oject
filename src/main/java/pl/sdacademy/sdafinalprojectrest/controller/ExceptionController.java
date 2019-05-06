package pl.sdacademy.sdafinalprojectrest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.sdacademy.sdafinalprojectrest.model.PropertyValidationError;

import javax.validation.ConstraintViolationException;
import java.util.Collection;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Collection<PropertyValidationError> handleConstraintViolationException(ConstraintViolationException e){
        return e.getConstraintViolations()
                .stream()
                .map(violation -> new PropertyValidationError(
                        violation.getPropertyPath().toString(),
                        violation.getMessage()))
                .collect(Collectors.toList());
    }
}
