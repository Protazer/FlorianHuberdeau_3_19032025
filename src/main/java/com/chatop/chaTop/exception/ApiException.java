package com.chatop.chaTop.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Custom exception class for handling API-related errors.
 * This exception includes error message and his HTTP status code.
 */
@Getter
public class ApiException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;

    public ApiException(final String message, final HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
