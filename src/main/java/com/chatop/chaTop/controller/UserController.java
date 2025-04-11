package com.chatop.chaTop.controller;

import com.chatop.chaTop.payload.request.UserLoginRequestDto;
import com.chatop.chaTop.payload.request.UserRegisterRequestDto;
import com.chatop.chaTop.payload.response.GetUserResponseDto;
import com.chatop.chaTop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class UserController {

	private final UserService userService;

	public UserController(final UserService userService) {
		this.userService = userService;
	}


	@RequestMapping("/me")
	public ResponseEntity<GetUserResponseDto> getUser() {
		String email = "flo@gmail.com";
		try {
			GetUserResponseDto user = userService.getUser(email);
			return ResponseEntity.ok().body(user);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@RequestMapping("/login")
	public ResponseEntity<?> loginUser(@Valid @RequestBody UserLoginRequestDto user) {
		try {
			userService.loginUser(user);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@RequestMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterRequestDto user) {
		try {
			userService.registerUser(user);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
