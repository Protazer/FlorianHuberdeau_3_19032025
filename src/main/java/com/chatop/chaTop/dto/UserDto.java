package com.chatop.chaTop.dto;

import java.util.Date;

public record UserDto(int id,
                      String email, String name, Date createdAt, Date updatedAt) {

}
