package com.chatop.chaTop.service;

import com.chatop.chaTop.mapper.RentalMapper;
import com.chatop.chaTop.model.Rental;
import com.chatop.chaTop.payload.request.CreateRental;
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
import java.util.List;
import java.util.Optional;


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

    public CreateRentalResponse addRental(CreateRental request, MultipartFile picture) {
        try {
            var pic = cloudinary.uploader().upload(picture, ObjectUtils.asMap("folder", "/rentalImg/"));
            int ownerId = 2;
            CreateRental formattedRental = new CreateRental(request.name(), request.surface(), request.price(), pic.get("url").toString(), request.description(), ownerId);
            Rental newRental = rentalMapper.toEntity(formattedRental);
            return new CreateRentalResponse("Rental created !");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to create rental.");
        }

    }
}