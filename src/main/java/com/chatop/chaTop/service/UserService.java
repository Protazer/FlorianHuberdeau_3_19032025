package com.chatop.chaTop.service;

import com.chatop.chaTop.mapper.UserMapper;
import com.chatop.chaTop.model.User;
import com.chatop.chaTop.payload.request.UserLogin;
import com.chatop.chaTop.payload.request.UserRegister;
import com.chatop.chaTop.payload.response.GetUser;
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

	public void registerUser(UserRegister user) {
		try {
			User newUser = this.userMapper.UserRegisterToEntity(user);
			this.userRepository.save(newUser);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void loginUser(UserLogin user) {
		try {
			System.out.println(user);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public GetUser getUser(String email) {
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
