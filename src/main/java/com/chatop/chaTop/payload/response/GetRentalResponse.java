package com.chatop.chaTop.payload.response;

public record GetRentalResponse(int id, String name, int surface, int price, String picture, String description,
                                int ownerId, String created_at, String updated_at) {
}
