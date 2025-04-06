package com.chatop.chaTop.payload.response;

import java.time.LocalDate;

public record GetUser(int id, String name, String email, LocalDate created_at, LocalDate updated_at) {
}
