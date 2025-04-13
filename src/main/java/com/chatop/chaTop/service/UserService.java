package com.chatop.chaTop.service;

import com.chatop.chaTop.exception.ApiException;
import com.chatop.chaTop.mapper.UserMapper;
import com.chatop.chaTop.model.User;
import com.chatop.chaTop.payload.request.UserLoginRequestDto;
import com.chatop.chaTop.payload.request.UserRegisterRequestDto;
import com.chatop.chaTop.payload.response.AuthUserResponseDto;
import com.chatop.chaTop.payload.response.GetUserResponseDto;
import com.chatop.chaTop.repository.UserRepository;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.chatop.chaTop.utils.Helpers.formatDate;

@Data
@Service
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final JWTService jwtService;

	public UserService(final UserRepository userRepository, final UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder, JWTService jwtService) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.jwtService = jwtService;
	}

	public AuthUserResponseDto registerUser(UserRegisterRequestDto user) {
		Optional<User> userExist = userRepository.findByEmail(user.email());
		if (userExist.isPresent()) {
			throw new ApiException("User Already exist", HttpStatus.BAD_REQUEST);
		}
		User newUser = userMapper.UserRegisterToEntity(user);
		userRepository.save(newUser);
		String token = jwtService.generateToken(newUser);
		return new AuthUserResponseDto(token);
	}

	public AuthUserResponseDto loginUser(UserLoginRequestDto user) {
		Optional<User> findUser = userRepository.findByEmail(user.login());
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

	public GetUserResponseDto getUser(JwtAuthenticationToken token) {
		int userId = Integer.parseInt(token.getToken().getSubject());
		Optional<User> user = this.userRepository.findById(userId);
		if (user.isPresent()) {
			String updatedDate = formatDate(user.get().getUpdatedAt());
			String createdAt = formatDate(user.get().getCreatedAt());
			return this.userMapper.toDto(user.get(), createdAt, updatedDate);
		} else {
			throw new ApiException("User not found", HttpStatus.NOT_FOUND);
		}

	}

	public GetUserResponseDto getUserById(final int id) {
		Optional<User> user = this.userRepository.findById(id);
		if (user.isPresent()) {
			String updatedDate = formatDate(user.get().getUpdatedAt());
			String createdAt = formatDate(user.get().getCreatedAt());
			return this.userMapper.toDto(user.get(), createdAt, updatedDate);
		} else {
			throw new ApiException("User not found", HttpStatus.NOT_FOUND);
		}

	}
}
