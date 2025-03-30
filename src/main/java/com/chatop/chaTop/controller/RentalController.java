package com.chatop.chaTop.controller;

import com.chatop.chaTop.payload.request.CreateRental;
import com.chatop.chaTop.payload.response.CreateRentalResponse;
import com.chatop.chaTop.payload.response.GetAllRentalsResponse;
import com.chatop.chaTop.payload.response.GetRentalResponse;
import com.chatop.chaTop.service.RentalService;
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
    public ResponseEntity<GetAllRentalsResponse> getRentals() {
        try {
            GetAllRentalsResponse rentals = rentalService.getRentals();
            return new ResponseEntity<GetAllRentalsResponse>(rentals, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetRentalResponse> getRentalById(@PathVariable final int id) {
        try {
            GetRentalResponse rental = rentalService.getRentalById(id);
            return new ResponseEntity<>(rental, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "", consumes = {"multipart/form-data"})
    public ResponseEntity<CreateRentalResponse> addRental(@RequestBody CreateRental request, @RequestPart MultipartFile picture) {
        try {
            CreateRentalResponse createRentalResponse = rentalService.addRental(request, picture);
            return ResponseEntity.ok().body(createRentalResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
