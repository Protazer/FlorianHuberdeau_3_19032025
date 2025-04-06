package com.chatop.chaTop.service;

import com.chatop.chaTop.mapper.RentalMapper;
import com.chatop.chaTop.model.Rental;
import com.chatop.chaTop.payload.response.CreateRentalResponse;
import com.chatop.chaTop.payload.response.GetAllRentalsResponse;
import com.chatop.chaTop.payload.response.GetRentalResponse;
import com.chatop.chaTop.repository.RentalRepository;
import com.chatop.chaTop.utils.Helpers;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Data
@Service
public class RentalService {

    private RentalRepository rentalRepository;
    private RentalMapper rentalMapper;
    private Cloudinary cloudinary;
    private Helpers helpers;

    public RentalService(final RentalRepository rentalRepository, final RentalMapper rentalMapper, Cloudinary cloudinary, Helpers helpers) {
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
        this.cloudinary = cloudinary;
        this.helpers = helpers;
    }

    public GetRentalResponse getRentalById(final int id) {
        Optional<Rental> rental = this.rentalRepository.findById(id);
        if (rental.isPresent()) {
            String updatedDate = this.helpers.formatDate(rental.get().getUpdatedAt());
            String createdAt = this.helpers.formatDate(rental.get().getCreatedAt());

            return this.rentalMapper.toDtoRental(rental.get(), createdAt, updatedDate);
        }
        throw new RuntimeException();
    }

    public GetAllRentalsResponse getRentals() {
        Iterable<Rental> rentals = this.rentalRepository.findAll();

        List<GetRentalResponse> formattedRentals = new ArrayList<>();

        rentals.iterator().forEachRemaining((Rental rental) -> {
            String updatedDate = this.helpers.formatDate(rental.getUpdatedAt());
            String createdAt = this.helpers.formatDate(rental.getCreatedAt());
            GetRentalResponse formattedRental = this.rentalMapper.toDtoRental(rental, createdAt, updatedDate);
            formattedRentals.add(formattedRental);
        });

        return this.rentalMapper.toDtoRentalList(formattedRentals);
    }

    public CreateRentalResponse addRental(MultipartFile picture, String name, int surface, int price, String description) {
        try {
            int ownerId = 3;

            String pictureUrl = this.cloudinary.uploader().upload(picture.getBytes(), ObjectUtils.asMap(
                    "use_filename", true,
                    "unique_filename", false,
                    "overwrite", true
            )).get("url").toString();

            Rental newRental = new Rental();
            newRental.setName(name);
            newRental.setSurface(surface);
            newRental.setPrice(price);
            newRental.setDescription(description);
            newRental.setPicture(pictureUrl);
            newRental.setOwnerId(ownerId);
            this.rentalRepository.save(newRental);
            return new CreateRentalResponse("Rental created !");

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to create rental.");
        }

    }

    public CreateRentalResponse updateRental(final int id, String name, int surface, int price, String description) {
        int ownerId = 3;
        Optional<Rental> oldRental = this.rentalRepository.findById(id);

        if (oldRental.isPresent()) {
            Rental oldRentalValues = oldRental.get();
            LocalDate updatedDate = LocalDate.now();

            Rental updatedRental = new Rental();
            updatedRental.setId(oldRentalValues.getId());
            updatedRental.setName(name);
            updatedRental.setSurface(surface);
            updatedRental.setPrice(price);
            updatedRental.setDescription(description);
            updatedRental.setPicture(oldRentalValues.getPicture());
            updatedRental.setOwnerId(oldRentalValues.getOwnerId());
            updatedRental.setCreatedAt(oldRentalValues.getCreatedAt());
            updatedRental.setUpdatedAt(updatedDate);
            this.rentalRepository.save(updatedRental);
            return new CreateRentalResponse("Rental updated !");
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to update rental.");
        }
    }


}