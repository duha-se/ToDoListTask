package com.toDoList.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle IOExceptions
    @ExceptionHandler(IOException.class)
    public ResponseEntity<ProblemDetail> handle(IOException e) {
        HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(statusCode, e.getMessage());
        return ResponseEntity.status(statusCode).body(detail);
    }

    // Handle ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ProblemDetail> handle(ResourceNotFoundException e) {
        HttpStatus statusCode = HttpStatus.NOT_FOUND;
        String resourceName = e.getResourceName();
        Long resourceId = e.getResourceId();
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(statusCode, String.format("%s not found: %s", resourceName, resourceId));
        detail.setTitle("Resource not found");
        return ResponseEntity.status(statusCode).body(detail);
    }

    // Handle IllegalArgumentException or similar exceptions
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ProblemDetail> handleIllegalArgument(Exception e) {
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(statusCode, e.getMessage());
        return ResponseEntity.status(statusCode).body(detail);
    }

    // Handle ConstraintViolationException for Date Format and Custom Validation
    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handle(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        List<Violation> violationDetails = new LinkedList<>();
        for (ConstraintViolation<?> violation : violations) {
            Violation violationDetail = new Violation(violation.getPropertyPath().toString(), violation.getMessage());
            violationDetails.add(violationDetail);
        }

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Invalid Request");
        problemDetail.setTitle("Validation Failed");
        problemDetail.setProperty("invalidParameters", violationDetails);
        return problemDetail;
    }

    // Handle MethodArgumentNotValidException (for Null/Empty Title and Other Field Errors)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handle(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<Violation> violations = new LinkedList<>();
        for (FieldError fieldError : fieldErrors) {
            Violation violation = new Violation(fieldError.getField(), fieldError.getDefaultMessage());
            violations.add(violation);
        }

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Invalid Request");
        problemDetail.setTitle("Validation Failed");
        problemDetail.setProperty("invalidParameters", violations);
        return problemDetail;
    }

    // Handle general exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleGeneralException(Exception e) {
        HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(statusCode, "An unexpected error occurred.");
        detail.setTitle("Unexpected Error");
        return ResponseEntity.status(statusCode).body(detail);
    }
}
