package com.chatop.chaTop.service;

import com.chatop.chaTop.mapper.UserMapper;
import com.chatop.chaTop.model.User;
import com.chatop.chaTop.payload.request.UserLoginRequestDto;
import com.chatop.chaTop.payload.request.UserRegisterRequestDto;
import com.chatop.chaTop.payload.response.GetUserResponseDto;
import com.chatop.chaTop.repository.UserRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.chatop.chaTop.utils.Helpers.formatDate;

@Data
@Service
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public UserService(final UserRepository userRepository, final UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	public void registerUser(UserRegisterRequestDto user) {
		User newUser = this.userMapper.UserRegisterToEntity(user);
		this.userRepository.save(newUser);

	}

	public void loginUser(UserLoginRequestDto user) {
		try {
			System.out.println(user);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public GetUserResponseDto getUser(String email) {
		Optional<User> user = this.userRepository.findByEmail(email);
		if (user.isPresent()) {
			String updatedDate = formatDate(user.get().getUpdatedAt());
			String createdAt = formatDate(user.get().getCreatedAt());
			return this.userMapper.toDto(user.get(), createdAt, updatedDate);
		} else {
			throw new RuntimeException();
		}

	}
}
