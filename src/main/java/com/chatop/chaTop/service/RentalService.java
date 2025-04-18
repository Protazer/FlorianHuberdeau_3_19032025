package com.chatop.chaTop.service;

import com.chatop.chaTop.exception.ApiException;
import com.chatop.chaTop.exception.GlobalExceptionHandler;
import com.chatop.chaTop.mapper.RentalMapper;
import com.chatop.chaTop.model.Rental;
import com.chatop.chaTop.payload.request.PostRentalRequestDto;
import com.chatop.chaTop.payload.response.CreateRentalResponseDto;
import com.chatop.chaTop.payload.response.GetAllRentalsResponseDto;
import com.chatop.chaTop.payload.response.GetRentalResponseDto;
import com.chatop.chaTop.repository.RentalRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	private GlobalExceptionHandler globalHandler;

	public RentalService(final RentalRepository rentalRepository, final RentalMapper rentalMapper, Cloudinary cloudinary) {
		this.rentalRepository = rentalRepository;
		this.rentalMapper = rentalMapper;
		this.cloudinary = cloudinary;
	}

	public GetRentalResponseDto getRentalById(final int id) {
		Optional<Rental> rental = rentalRepository.findById(id);
		if (rental.isPresent()) {
			String updatedDate = formatDate(rental.get().getUpdatedAt());
			String createdAt = formatDate(rental.get().getCreatedAt());

			return rentalMapper.toDtoRental(rental.get(), createdAt, updatedDate);
		} else {
			throw new ApiException("Failed to get Rental", HttpStatus.NOT_FOUND);
		}
	}

	public GetAllRentalsResponseDto getRentals() {
		Iterable<Rental> rentals = rentalRepository.findAll();

		List<GetRentalResponseDto> formattedRentals = new ArrayList<>();

		rentals.iterator().forEachRemaining((Rental rental) -> {
			String updatedDate = formatDate(rental.getUpdatedAt());
			String createdAt = formatDate(rental.getCreatedAt());
			GetRentalResponseDto formattedRental = rentalMapper.toDtoRental(rental, createdAt, updatedDate);
			formattedRentals.add(formattedRental);
		});

		return rentalMapper.toDtoRentalList(formattedRentals);
	}

	public CreateRentalResponseDto addRental(MultipartFile picture, PostRentalRequestDto request) {
		try {
			int ownerId = 3;

			String pictureUrl = this.cloudinary.uploader().upload(picture.getBytes(), ObjectUtils.asMap(
					"use_filename", true,
					"unique_filename", false,
					"overwrite", true
			)).get("url").toString();

			Rental newRental = rentalMapper.toCreateEntity(request, pictureUrl, ownerId);
			rentalRepository.save(newRental);
			return new CreateRentalResponseDto("Rental created !");

		} catch (Exception e) {
			throw new ApiException("Failed to create rental.", HttpStatus.BAD_REQUEST);
		}

	}

	public CreateRentalResponseDto updateRental(int id, PostRentalRequestDto request) {
		int ownerId = 3;
		Optional<Rental> oldRental = rentalRepository.findById(id);

		if (oldRental.isPresent()) {
			Rental oldRentalValues = oldRental.get();
			Date updatedDate = new Date();

			Rental updatedRental = rentalMapper.toUpdateEntity(request, oldRentalValues.getId(), oldRentalValues.getPicture(), ownerId, oldRentalValues.getCreatedAt(), updatedDate);
			rentalRepository.save(updatedRental);
			return new CreateRentalResponseDto("Rental updated !");
		} else {
			throw new ApiException("Failed to update rental.", HttpStatus.BAD_REQUEST);
		}
	}


}