package com.chatop.chaTop.service;

import com.chatop.chaTop.exception.ApiException;
import com.chatop.chaTop.mapper.UserMapper;
import com.chatop.chaTop.model.User;
import com.chatop.chaTop.payload.request.UserLoginRequestDto;
import com.chatop.chaTop.payload.request.UserRegisterRequestDto;
import com.chatop.chaTop.payload.response.AuthUserResponseDto;
import com.chatop.chaTop.payload.response.GetUserResponseDto;
import com.chatop.chaTop.repository.UserRepository;
import com.chatop.chaTop.utils.Helpers;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing users.
 */
@Data
@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private JWTService jwtService;
    private Helpers helpers;

    public UserService(final UserRepository userRepository, final UserMapper userMapper, final BCryptPasswordEncoder bCryptPasswordEncoder, final JWTService jwtService, final Helpers helpers) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtService = jwtService;
        this.helpers = helpers;
    }

    /**
     * Register a user using information from the registration request.
     *
     * @param user The registration request containing user details.
     * @return AuthUserResponseDto with generated token.
     */
    public AuthUserResponseDto registerUser(final UserRegisterRequestDto user) {
        Optional<User> userExist = userRepository.findByEmail(user.email());
        if (userExist.isPresent()) {
            throw new ApiException("User Already exist", HttpStatus.BAD_REQUEST);
        }
        User newUser = userMapper.UserRegisterToEntity(user);
        userRepository.save(newUser);
        String token = jwtService.generateToken(newUser);
        return new AuthUserResponseDto(token);
    }

    /**
     * login a user using information from the registration request.
     *
     * @param user The registration request containing user details.
     * @return AuthUserResponseDto with generated token.
     */
    public AuthUserResponseDto loginUser(final UserLoginRequestDto user) {
        Optional<User> findUser = userRepository.findByEmail(user.email());
        if (findUser.isEmpty()) {
            throw new ApiException("Email not found", HttpStatus.UNAUTHORIZED);
        }
        boolean isLoginCorrect = bCryptPasswordEncoder.matches(user.password(), findUser.get().getPassword());
        if (!isLoginCorrect) {
            throw new ApiException("Email/Password invalid", HttpStatus.UNAUTHORIZED);
        }

        String token = jwtService.generateToken(findUser.get());
        return new AuthUserResponseDto(token);
    }

    /**
     * Retrieve a user by token.
     *
     * @param token The JwtAuthenticationToken.
     * @return GetUserResponseDto The found User.
     */
    public GetUserResponseDto getUser(final JwtAuthenticationToken token) {
        int userId = Integer.parseInt(token.getToken().getSubject());
        Optional<User> user = this.userRepository.findById(userId);
        if (user.isPresent()) {
            String updatedDate = helpers.formatDate(user.get().getUpdatedAt());
            String createdAt = helpers.formatDate(user.get().getCreatedAt());
            return this.userMapper.toDto(user.get(), createdAt, updatedDate);
        } else {
            throw new ApiException("User not found", HttpStatus.NOT_FOUND);
        }

    }

    /**
     * Retrieve a user by his id.
     *
     * @param id The identifier of the user.
     * @return GetUserResponseDto The found User.
     */
    public GetUserResponseDto getUserById(final int id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            String updatedDate = helpers.formatDate(user.get().getUpdatedAt());
            String createdAt = helpers.formatDate(user.get().getCreatedAt());
            return this.userMapper.toDto(user.get(), createdAt, updatedDate);
        } else {
            throw new ApiException("User not found", HttpStatus.NOT_FOUND);
        }

    }
}
