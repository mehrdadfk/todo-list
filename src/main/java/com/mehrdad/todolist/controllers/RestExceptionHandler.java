package com.mehrdad.todolist.controllers;

import com.mehrdad.todolist.exceptions.EntityNotFoundException;
import com.mehrdad.todolist.model.ErrorResponseDTO;
import com.mehrdad.todolist.model.ValidationErrorDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Global exception handler to make all the error response follow same json format.
 * main benefit of this class is to handle properly the body of error like "not found" in contrary to @ResponseStatus(value = HttpStatus.NOT_FOUND)
 *
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    /**
     * Handle EntityNotFoundException. Triggered when an entity was not found.
     * @param ex  the exception to handle
     * @return a ResponseEntity for the response to use.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        ErrorResponseDTO apiError = new ErrorResponseDTO(NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(apiError, NOT_FOUND);
    }

    /**
     * Handle ConstraintViolationException. Triggered when an entity was not found.
     * @param ex  the exception to handle
     * @return a ResponseEntity for the response to use.
     */
    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex) {
        ErrorResponseDTO apiError = new ErrorResponseDTO(BAD_REQUEST.value(), ex.getMessage());
        for (ConstraintViolation fieldError : ex.getConstraintViolations()) {
            ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO(fieldError.getConstraintDescriptor().getMessageTemplate(), fieldError.getMessage());
            apiError.addErrorsItem(validationErrorDTO);
        }
        return new ResponseEntity<>(apiError, BAD_REQUEST);
    }

}
