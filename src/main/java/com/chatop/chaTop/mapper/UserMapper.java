package com.chatop.chaTop.mapper;

import com.chatop.chaTop.model.User;
import com.chatop.chaTop.payload.request.UserRegisterRequestDto;
import com.chatop.chaTop.payload.response.GetUserResponseDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * This class represents a mapper for converting User Entity to
 * User DTO objects and User DTO objects to User Entity.
 */
@Service
public class UserMapper {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserMapper(final BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * Converts a User Entity to GetUserResponseDto objects.
     *
     * @param user        The User Entity to be converted.
     * @param createdAt   The User Entity created date.
     * @param updatedDate The User Entity updated date.
     * @return The GetUserResponseDto objects.
     */
    public GetUserResponseDto toDto(final User user, final String createdAt, final String updatedDate) {
        return new GetUserResponseDto(user.getId(), user.getName(), user.getEmail(), createdAt, updatedDate);
    }

    /**
     * Converts a UserRegisterRequestDto to User Entity.
     *
     * @param user The UserRegisterRequestDto to be converted.
     * @return The User Entity.
     */
    public User UserRegisterToEntity(final UserRegisterRequestDto user) {
        User newUser = new User();
        newUser.setName(user.name());
        newUser.setEmail(user.email());
        newUser.setPassword(bCryptPasswordEncoder.encode(user.password()));
        return newUser;
    }
}
