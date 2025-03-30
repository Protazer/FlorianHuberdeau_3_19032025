package com.chatop.chaTop.payload.response;

import java.util.Date;

public record GetUser(int id, String name, String email, Date created_at, Date updated_at) {
}
