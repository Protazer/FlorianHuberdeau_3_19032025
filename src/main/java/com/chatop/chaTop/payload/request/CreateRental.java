package com.chatop.chaTop.payload.request;

public record CreateRental(String name, float surface, float price, String picture, String description, int ownerId) {
}
