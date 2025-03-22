package com.chatop.chaTop.service;

import com.chatop.chaTop.model.Rental;
import com.chatop.chaTop.repository.RentalRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Data
@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;


    public Optional<Rental> getRentalById(final Long id) {
        return rentalRepository.findById(id);
    }

    public Iterable<Rental> getRentals() {
        return rentalRepository.findAll();
    }

    public Rental saveRental(Rental rental) {
        return rentalRepository.save(rental);
    }

}
