package com.chatop.chaTop.mapper;

import com.chatop.chaTop.model.Rental;
import com.chatop.chaTop.payload.request.PostRentalRequestDto;
import com.chatop.chaTop.payload.response.GetAllRentalsResponseDto;
import com.chatop.chaTop.payload.response.GetRentalResponseDto;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RentalMapper {
    public GetAllRentalsResponseDto toDtoRentalList(List<GetRentalResponseDto> rentals) {
        return new GetAllRentalsResponseDto(rentals);
    }

    public GetRentalResponseDto toDtoRental(Rental rental, String createdAt, String updatedAt) {
        return new GetRentalResponseDto(rental.getId(), rental.getName(), rental.getSurface(), rental.getPrice(), rental.getPicture(), rental.getDescription(), rental.getOwnerId(), createdAt, updatedAt);
    }

    public Rental toCreateEntity(final PostRentalRequestDto request, final String picture, final int ownerId) {
        Rental newRental = new Rental();
        newRental.setName(request.name());
        newRental.setSurface(request.surface());
        newRental.setPrice(request.price());
        newRental.setDescription(request.description());
        newRental.setPicture(picture);
        newRental.setOwnerId(ownerId);
        return newRental;
    }

    public Rental toUpdateEntity(final PostRentalRequestDto request, final int rentalId, final String picture, final int ownerId, Date createdAt, Date updatedAt) {
        Rental newRental = new Rental();
        newRental.setId(rentalId);
        newRental.setName(request.name());
        newRental.setSurface(request.surface());
        newRental.setPrice(request.price());
        newRental.setDescription(request.description());
        newRental.setPicture(picture);
        newRental.setOwnerId(ownerId);
        newRental.setCreatedAt(createdAt);
        newRental.setUpdatedAt(updatedAt);
        return newRental;
    }

}
