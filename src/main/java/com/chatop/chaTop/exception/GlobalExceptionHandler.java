package com.chatop.chaTop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for handling instances of ApiException in the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles instances of MethodArgumentNotValidException and constructs a proper response
     * entity with details of the exception.
     *
     * @param ex The ApiException to handle.
     * @return A ResponseEntity with an appropriate status code and error details.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles instances of ApiException and constructs a proper response
     * entity with details of the exception.
     *
     * @param exception The ApiException to handle.
     * @return A ResponseEntity with an appropriate status code and error details.
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleApiException(ApiException exception) {
        Map<String, String> error = new HashMap<>();
        error.put("message", exception.getMessage());
        return new ResponseEntity<>(error, exception.getHttpStatus());
    }

    /**
     * Handles instances of RuntimeException and constructs a proper response
     * entity with details of the exception.
     *
     * @param exception The ApiException to handle.
     * @return A ResponseEntity with an appropriate status code and error details.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
        Map<String, String> error = new HashMap<>();
        error.put("message", exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

