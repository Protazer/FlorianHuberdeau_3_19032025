package com.chatop.chaTop.mapper;

import com.chatop.chaTop.model.User;
import com.chatop.chaTop.payload.request.UserRegisterRequestDto;
import com.chatop.chaTop.payload.response.GetUserResponseDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserMapper(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public GetUserResponseDto toDto(User user, String createdAt, String updatedDate) {
        return new GetUserResponseDto(user.getId(), user.getEmail(), user.getName(), createdAt, updatedDate);
    }

    public User UserRegisterToEntity(UserRegisterRequestDto user) {
        User newUser = new User();
        newUser.setName(user.name());
        newUser.setEmail(user.email());
        newUser.setPassword(bCryptPasswordEncoder.encode(user.password()));
        return newUser;
    }
}
