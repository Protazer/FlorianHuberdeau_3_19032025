package com.chatop.chaTop.controller;

import com.chatop.chaTop.payload.request.UserLoginRequestDto;
import com.chatop.chaTop.payload.request.UserRegisterRequestDto;
import com.chatop.chaTop.payload.response.AuthUserResponseDto;
import com.chatop.chaTop.payload.response.GetUserResponseDto;
import com.chatop.chaTop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

	private final UserService userService;


	public AuthController(final UserService userService) {
		this.userService = userService;
	}


	@RequestMapping("/me")
	public ResponseEntity<GetUserResponseDto> getUser(JwtAuthenticationToken token) {
		GetUserResponseDto user = userService.getUser(token);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}


	@RequestMapping("/login")
	public ResponseEntity<AuthUserResponseDto> loginUser(@Valid @RequestBody UserLoginRequestDto user) {
		AuthUserResponseDto authUserResponse = userService.loginUser(user);
		return new ResponseEntity<>(authUserResponse, HttpStatus.OK);

	}

	@RequestMapping("/register")
	public ResponseEntity<AuthUserResponseDto> registerUser(@Valid @RequestBody UserRegisterRequestDto user) {
		AuthUserResponseDto authUserResponseDto = userService.registerUser(user);
		return new ResponseEntity<>(authUserResponseDto, HttpStatus.OK);
	}

}
