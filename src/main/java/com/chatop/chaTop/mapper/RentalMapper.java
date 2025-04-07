package com.chatop.chaTop.mapper;

import com.chatop.chaTop.model.Rental;
import com.chatop.chaTop.payload.response.GetAllRentalsResponse;
import com.chatop.chaTop.payload.response.GetRentalResponse;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RentalMapper {
	public GetAllRentalsResponse toDtoRentalList(List<GetRentalResponse> rentals) {
		return new GetAllRentalsResponse(rentals);
	}

	public GetRentalResponse toDtoRental(Rental rental, String createdAt, String updatedAt) {
		return new GetRentalResponse(rental.getId(), rental.getName(), rental.getSurface(), rental.getPrice(), rental.getPicture(), rental.getDescription(), rental.getOwnerId(), createdAt, updatedAt);
	}


	public Rental toCreateEntity(final String name, final int surface, final int price, final String description, final String picture, final int ownerId) {
		Rental newRental = new Rental();
		newRental.setName(name);
		newRental.setSurface(surface);
		newRental.setPrice(price);
		newRental.setDescription(description);
		newRental.setPicture(picture);
		newRental.setOwnerId(ownerId);
		return newRental;
	}

	public Rental toUpdateEntity(final int id, final String name, final int surface, final int price, final String description, final String picture, final int ownerId, Date createdAt, Date updatedAt) {
		Rental newRental = new Rental();
		newRental.setId(id);
		newRental.setName(name);
		newRental.setSurface(surface);
		newRental.setPrice(price);
		newRental.setDescription(description);
		newRental.setPicture(picture);
		newRental.setOwnerId(ownerId);
		newRental.setCreatedAt(createdAt);
		newRental.setUpdatedAt(updatedAt);
		return newRental;
	}

}
