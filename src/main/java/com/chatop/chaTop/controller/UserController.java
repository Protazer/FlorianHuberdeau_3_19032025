package com.chatop.chaTop.controller;

import com.chatop.chaTop.payload.request.UserLogin;
import com.chatop.chaTop.payload.request.UserRegister;
import com.chatop.chaTop.payload.response.GetUser;
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
	public ResponseEntity<GetUser> getUser() {
		String email = "flo@gmail.com";
		try {
			GetUser user = userService.getUser(email);
			return ResponseEntity.ok().body(user);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@RequestMapping("/login")
	public ResponseEntity<?> loginUser(@Valid @RequestBody UserLogin user) {
		try {
			userService.loginUser(user);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@RequestMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegister user) {
		try {
			userService.registerUser(user);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
