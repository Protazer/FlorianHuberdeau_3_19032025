package com.chatop.chaTop.controller;

import com.chatop.chaTop.payload.request.PostRentalRequestDto;
import com.chatop.chaTop.payload.response.CreateRentalResponseDto;
import com.chatop.chaTop.payload.response.GetAllRentalsResponseDto;
import com.chatop.chaTop.payload.response.GetRentalResponseDto;
import com.chatop.chaTop.service.RentalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/rentals")
public class RentalController {

	private final RentalService rentalService;

	public RentalController(final RentalService rentalService) {
		this.rentalService = rentalService;
	}

	@GetMapping("")
	public ResponseEntity<GetAllRentalsResponseDto> getRentals() {
		GetAllRentalsResponseDto rentals = rentalService.getRentals();
		return new ResponseEntity<GetAllRentalsResponseDto>(rentals, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<GetRentalResponseDto> getRentalById(@PathVariable final int id) {
		GetRentalResponseDto rental = rentalService.getRentalById(id);
		return new ResponseEntity<>(rental, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CreateRentalResponseDto> addRental(@RequestPart(value = "picture") MultipartFile picture, @Valid @ModelAttribute PostRentalRequestDto request) {
		CreateRentalResponseDto createRentalResponse = rentalService.addRental(picture, request);
		return ResponseEntity.ok().body(createRentalResponse);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<CreateRentalResponseDto> updateRental(@PathVariable int id, @Valid @ModelAttribute PostRentalRequestDto request) {
		CreateRentalResponseDto createRentalResponse = rentalService.updateRental(id, request);
		return ResponseEntity.ok().body(createRentalResponse);
	}
}
