package com.chatop.chaTop.payload.response;

import java.util.List;

public record GetAllRentalsResponse(List<GetRentalResponse> rentals) {
}
