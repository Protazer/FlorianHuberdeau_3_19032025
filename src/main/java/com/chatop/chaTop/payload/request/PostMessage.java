package com.chatop.chaTop.payload.request;

import jakarta.validation.constraints.NotEmpty;

public record PostMessage(@NotEmpty String message, @NotEmpty int user_id, @NotEmpty int rental_id) {
}
