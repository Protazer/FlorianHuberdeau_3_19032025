package com.chatop.chaTop.controller;

import com.chatop.chaTop.payload.response.GetUserResponseDto;
import com.chatop.chaTop.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<GetUserResponseDto> getUserById(@PathVariable final int id) {
		GetUserResponseDto user = userService.getUserById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
