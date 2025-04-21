package com.chatop.chaTop.payload.response;

/**
 * Response payload for get specific user.
 */
public record GetUserResponseDto(int id, String name, String email,
                                 String created_at, String updated_at) {
}
