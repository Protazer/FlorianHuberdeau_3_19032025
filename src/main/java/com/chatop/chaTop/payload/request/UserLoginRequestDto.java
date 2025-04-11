package com.chatop.chaTop.payload.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserLoginRequestDto(
		@NotEmpty(message = "Login is mandatory") @Email(message = "Email must be a valid address") String login,
		@NotEmpty(message = "Password is mandatory ") String password) {
}
