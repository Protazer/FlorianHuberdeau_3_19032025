package com.chatop.chaTop.payload.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

/**
 * Request payload for user login.
 */
public record UserLoginRequestDto(
        @NotEmpty(message = "Email is mandatory") @Email(message = "Email must be a valid address") String email,
        @NotEmpty(message = "Password is mandatory ") String password) {
}
