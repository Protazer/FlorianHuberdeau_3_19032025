package com.chatop.chaTop.dto;

import java.util.Date;

public record RentalDto(int id, String name, float surface, float price, String picture, String description,
                        int ownerId, Date createdAt, Date updatedAt) {
}
