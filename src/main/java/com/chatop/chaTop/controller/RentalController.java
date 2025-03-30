package com.chatop.chaTop.controller;

import com.chatop.chaTop.payload.response.GetAllRentalsResponse;
import com.chatop.chaTop.payload.response.GetRentalResponse;
import com.chatop.chaTop.service.RentalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
