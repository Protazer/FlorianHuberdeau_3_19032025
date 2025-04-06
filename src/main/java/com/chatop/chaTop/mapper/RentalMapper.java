package com.chatop.chaTop.mapper;

import com.chatop.chaTop.model.Rental;
import com.chatop.chaTop.payload.response.GetAllRentalsResponse;
import com.chatop.chaTop.payload.response.GetRentalResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalMapper {
    public GetAllRentalsResponse toDtoRentalList(List<GetRentalResponse> rentals) {
        return new GetAllRentalsResponse(rentals);
    }

    public GetRentalResponse toDtoRental(Rental rental, String createdAt, String updatedAt) {
        return new GetRentalResponse(rental.getId(), rental.getName(), rental.getSurface(), rental.getPrice(), rental.getPicture(), rental.getDescription(), rental.getOwnerId(), createdAt, updatedAt);
    }


}
