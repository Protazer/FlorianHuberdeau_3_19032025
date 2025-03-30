package com.chatop.chaTop.mapper;

import com.chatop.chaTop.model.Rental;
import com.chatop.chaTop.payload.request.CreateRental;
import com.chatop.chaTop.payload.response.GetAllRentalsResponse;
import com.chatop.chaTop.payload.response.GetRentalResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalMapper {
    public GetAllRentalsResponse toDtoRentalList(List<GetRentalResponse> rentals) {
        return new GetAllRentalsResponse(rentals);
    }

    public GetRentalResponse toDtoRental(Rental rental) {
        return new GetRentalResponse(rental.getId(), rental.getName(), rental.getSurface(), rental.getPrice(), rental.getPicture(), rental.getDescription(), rental.getOwnerId(), rental.getCreatedAt(), rental.getUpdatedAt());
    }

    public Rental toEntity(CreateRental rental, int ownerId) {
        Rental NewRental = new Rental();
        NewRental.setName(rental.name());
        NewRental.setPicture(rental.picture());
        NewRental.setSurface(rental.surface());
        NewRental.setOwnerId(ownerId);
        NewRental.setDescription(rental.description());
        NewRental.setPrice(rental.price());

        return NewRental;
    }

}
