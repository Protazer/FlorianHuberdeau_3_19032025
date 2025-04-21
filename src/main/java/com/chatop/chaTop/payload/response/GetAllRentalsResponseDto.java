package com.chatop.chaTop.payload.response;

import java.util.List;

/**
 * Response payload for get rentals list.
 */
public record GetAllRentalsResponseDto(List<GetRentalResponseDto> rentals) {
}
