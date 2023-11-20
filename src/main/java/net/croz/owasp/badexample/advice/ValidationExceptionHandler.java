package net.croz.owasp.badexample.advice;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, List<String>> handleConstraintViolation(BindException ex) {
        Map<String, List<String>> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
            .forEach(error -> addError(error, errors));
        return errors;
    }

    private static void addError(FieldError error, Map<String, List<String>> errors) {
        List<String> fieldErrors = errors.computeIfAbsent(error.getField(), k -> new ArrayList<>());
        fieldErrors.add(error.getDefaultMessage());
    }

}