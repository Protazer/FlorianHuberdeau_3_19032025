package com.chatop.chaTop.payload.response;

import java.util.Date;


public record GetRentalResponse(int id, String name, float surface, float price, String picture, String description,
                                int ownerId, Date created_at, Date updated_at) {
}
