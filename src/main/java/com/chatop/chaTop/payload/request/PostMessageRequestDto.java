package com.chatop.chaTop.payload.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PostMessageRequestDto(@NotEmpty(message = "message is mandatory") String message,
                                    @NotNull(message = "user id is mandatory") Integer user_id,
                                    @NotNull(message = "rental id is mandatory") Integer rental_id) {
}
