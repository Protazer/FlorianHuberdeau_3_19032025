package com.chatop.chaTop.mapper;

import com.chatop.chaTop.dto.RentalDto;
import com.chatop.chaTop.model.Rental;
import org.springframework.stereotype.Service;

@Service
public class RentalMapper {
	public RentalDto toDto(Rental rental) {
		return new RentalDto(rental.getId(), rental.getName(), rental.getSurface(), rental.getPrice(), rental.getPicture(), rental.getDescription(), rental.getOwnerId(), rental.getCreatedAt(), rental.getUpdatedAt());
	}
}
