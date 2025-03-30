package com.chatop.chaTop.service;

import com.chatop.chaTop.mapper.RentalMapper;
import com.chatop.chaTop.model.Rental;
import com.chatop.chaTop.payload.response.GetAllRentalsResponse;
import com.chatop.chaTop.payload.response.GetRentalResponse;
import com.chatop.chaTop.repository.RentalRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Data
@Service
public class RentalService {


    private RentalRepository rentalRepository;
    private RentalMapper rentalMapper;

    public RentalService(final RentalRepository rentalRepository, final RentalMapper rentalMapper) {
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
    }

    public GetRentalResponse getRentalById(final int id) {
        Optional<Rental> rental = rentalRepository.findById(id);
        if (rental.isPresent()) {
            return rentalMapper.toDtoRental(rental.get());
        }
        throw new RuntimeException();
    }

    public GetAllRentalsResponse getRentals() {
        Iterable<Rental> rentals = rentalRepository.findAll();

        List<GetRentalResponse> formattedRentals = new ArrayList<>();

        rentals.iterator().forEachRemaining((Rental rental) -> {
            GetRentalResponse formattedRental = rentalMapper.toDtoRental(rental);
            formattedRentals.add(formattedRental);
        });

        return rentalMapper.toDtoRentalList(formattedRentals);
    }
}
