package com.chatop.chaTop.payload.response;

public record GetUser(int id, String name, String email, String created_at, String updated_at) {
}
