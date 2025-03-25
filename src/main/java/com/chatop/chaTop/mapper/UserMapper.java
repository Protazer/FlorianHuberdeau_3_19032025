package com.chatop.chaTop.mapper;

import com.chatop.chaTop.dto.UserDto;
import com.chatop.chaTop.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
	public UserDto toDto(User user) {
		return new UserDto(user.getId(), user.getEmail(), user.getName(), user.getCreatedAt(), user.getUpdatedAt());
	}
}
