package com.chatop.chaTop.payload.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserRegister(@NotEmpty @Email String email, @NotEmpty String name, @NotEmpty String password) {
}
