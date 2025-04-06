package com.chatop.chaTop.payload.request;


import jakarta.validation.constraints.NotEmpty;

public record PostRental(@NotEmpty String name, @NotEmpty int surface,
                         @NotEmpty int price,
                         @NotEmpty String description, int ownerId) {
}
