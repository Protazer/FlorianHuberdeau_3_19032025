package com.chatop.chaTop.payload.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserLogin(@NotEmpty @Email String login, @NotEmpty String password) {
}
