package com.chatop.chaTop.mapper;

import com.chatop.chaTop.model.Rental;
import com.chatop.chaTop.payload.request.PostRentalRequestDto;
import com.chatop.chaTop.payload.response.GetAllRentalsResponseDto;
import com.chatop.chaTop.payload.response.GetRentalResponseDto;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * This class represents a mapper for converting Rental Entity to
 * Rental DTO objects and Rental DTO objects to Rental Entity.
 */
@Service
public class RentalMapper {

    /**
     * Converts a list of GetRentalResponseDto to GetAllRentalsResponseDto objects.
     *
     * @param rentals The list of rentals to be converted.
     * @return The GetAllRentalsResponseDto objects.
     */
    public GetAllRentalsResponseDto toDtoRentalList(final List<GetRentalResponseDto> rentals) {
        return new GetAllRentalsResponseDto(rentals);
    }

    /**
     * Converts a Rental Entity to GetRentalResponseDto objects.
     *
     * @param rental    The list of rentals to be converted.
     * @param createdAt The created rental date.
     * @param updatedAt The updated rental date.
     * @return The GetRentalResponseDto objects.
     */
    public GetRentalResponseDto toDtoRental(final Rental rental, final String createdAt, final String updatedAt) {
        return new GetRentalResponseDto(rental.getId(), rental.getName(), rental.getSurface(), rental.getPrice(), rental.getPicture(), rental.getDescription(), rental.getOwnerId(), createdAt, updatedAt);
    }

    /**
     * Converts a PostRentalRequestDto objects to created Rental Entity.
     *
     * @param request The PostRentalRequestDto object to be converted.
     * @param picture The rental picture url.
     * @param ownerId The rental owner id.
     * @return The created Rental Entity.
     */
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

    /**
     * Converts a PostRentalRequestDto objects to updated Rental Entity.
     *
     * @param request   The PostRentalRequestDto object to be converted.
     * @param rentalId  The updated rental id.
     * @param picture   The rental picture url.
     * @param ownerId   The rental owner id.
     * @param createdAt The updated rental created date.
     * @param updatedAt The updated rental updated date.
     * @return The updated Rental Entity.
     */
    public Rental toUpdateEntity(final PostRentalRequestDto request, final int rentalId, final String picture, final int ownerId, final Date createdAt, final Date updatedAt) {
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
