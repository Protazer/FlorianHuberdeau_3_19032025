package com.chatop.chaTop.service;

import com.chatop.chaTop.mapper.RentalMapper;
import com.chatop.chaTop.model.Rental;
import com.chatop.chaTop.payload.response.CreateRentalResponse;
import com.chatop.chaTop.payload.response.GetAllRentalsResponse;
import com.chatop.chaTop.payload.response.GetRentalResponse;
import com.chatop.chaTop.repository.RentalRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.chatop.chaTop.utils.Helpers.formatDate;


@Data
@Service
public class RentalService {

	private RentalRepository rentalRepository;
	private RentalMapper rentalMapper;
	private Cloudinary cloudinary;

	public RentalService(final RentalRepository rentalRepository, final RentalMapper rentalMapper, Cloudinary cloudinary) {
		this.rentalRepository = rentalRepository;
		this.rentalMapper = rentalMapper;
		this.cloudinary = cloudinary;
	}

	public GetRentalResponse getRentalById(final int id) {
		Optional<Rental> rental = rentalRepository.findById(id);
		if (rental.isPresent()) {
			String updatedDate = formatDate(rental.get().getUpdatedAt());
			String createdAt = formatDate(rental.get().getCreatedAt());

			return this.rentalMapper.toDtoRental(rental.get(), createdAt, updatedDate);
		}
		throw new RuntimeException();
	}

	public GetAllRentalsResponse getRentals() {
		Iterable<Rental> rentals = rentalRepository.findAll();

		List<GetRentalResponse> formattedRentals = new ArrayList<>();

		rentals.iterator().forEachRemaining((Rental rental) -> {
			String updatedDate = formatDate(rental.getUpdatedAt());
			String createdAt = formatDate(rental.getCreatedAt());
			GetRentalResponse formattedRental = rentalMapper.toDtoRental(rental, createdAt, updatedDate);
			formattedRentals.add(formattedRental);
		});

		return rentalMapper.toDtoRentalList(formattedRentals);
	}

	public CreateRentalResponse addRental(MultipartFile picture, String name, int surface, int price, String description) {
		try {
			int ownerId = 3;

			String pictureUrl = this.cloudinary.uploader().upload(picture.getBytes(), ObjectUtils.asMap(
					"use_filename", true,
					"unique_filename", false,
					"overwrite", true
			)).get("url").toString();

			Rental newRental = rentalMapper.toCreateEntity(name, surface, price, description, pictureUrl, ownerId);
			rentalRepository.save(newRental);
			return new CreateRentalResponse("Rental created !");

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to create rental.");
		}

	}

	public CreateRentalResponse updateRental(final int id, String name, int surface, int price, String description) {
		int ownerId = 3;
		Optional<Rental> oldRental = rentalRepository.findById(id);

		if (oldRental.isPresent()) {
			Rental oldRentalValues = oldRental.get();
			Date updatedDate = new Date();

			Rental updatedRental = rentalMapper.toUpdateEntity(oldRentalValues.getId(), name, surface, price, description, oldRentalValues.getPicture(), ownerId, oldRentalValues.getCreatedAt(), updatedDate);
			rentalRepository.save(updatedRental);
			return new CreateRentalResponse("Rental updated !");
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to update rental.");
		}
	}


}