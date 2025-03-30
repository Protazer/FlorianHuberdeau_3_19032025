package com.chatop.chaTop.mapper;

import com.chatop.chaTop.model.User;
import com.chatop.chaTop.payload.response.GetUser;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public GetUser toDto(User user) {
        return new GetUser(user.getId(), user.getEmail(), user.getName(), user.getCreatedAt(), user.getUpdatedAt());
    }
}
