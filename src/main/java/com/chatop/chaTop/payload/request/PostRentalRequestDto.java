package com.chatop.chaTop.payload.request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Request payload for post rental.
 */
public record PostRentalRequestDto(
        @NotEmpty(message = "Name is mandatory") String name,
        @NotNull(message = "Surface in mandatory") @Positive(message = "Surface must be 1m2 minimum") Integer surface,
        @NotNull(message = "Price is mandatory") @Positive(message = "Price must be 1€ minimum") Integer price,
        @NotEmpty(message = "Description is mandatory") String description) {
}
