package com.chatop.chaTop.dto;

import java.util.Date;

public record RentalDto(Long id, String name, Float surface, Float price, String picture, String description,
                        Long ownerId, Date createdAt, Date updatedAt) {
}
