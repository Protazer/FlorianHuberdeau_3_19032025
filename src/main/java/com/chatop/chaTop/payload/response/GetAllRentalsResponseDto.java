package com.chatop.chaTop.payload.response;

import java.util.List;

public record GetAllRentalsResponseDto(List<GetRentalResponseDto> rentals) {
}
