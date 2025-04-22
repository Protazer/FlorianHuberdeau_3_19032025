package com.chatop.chaTop.controller;

import com.chatop.chaTop.payload.request.UserLoginRequestDto;
import com.chatop.chaTop.payload.request.UserRegisterRequestDto;
import com.chatop.chaTop.payload.response.AuthUserResponseDto;
import com.chatop.chaTop.payload.response.GetUserResponseDto;
import com.chatop.chaTop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling authentication-related operations.
 */
@RestController
@RequestMapping("api/auth")
public class AuthController {

	private final UserService userService;


	public AuthController(final UserService userService) {
		this.userService = userService;
	}


	/**
	 * Get user by token.
	 *
	 * @param token The JWTAuthenticationToken.
	 * @return ResponseEntity<GetUserResponseDto> A JWT if registration is successful.
	 */
	@GetMapping("/me")
	@Operation(summary = "Get User by token", description = "Return a user as per the token")
	@SecurityRequirement(name = "Bearer Authentication")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful retrieved user", content = @Content(schema = @Schema(implementation = GetUserResponseDto.class), examples = @ExampleObject(value = "{\n" +
					"  \"id\": 2,\n" +
					"\"name\": \"User Name\",\n" +
					"\"email\": \"test@test.com\",\n" +
					"\"created_at\": \"2022/02/02\",\n" +
					"\"updated_at\": \"2022/08/02\"  \n" +
					"}"), mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(), examples = @ExampleObject(value = "{\"message\": \"User not found\"}"), mediaType = "application/json")),
			@ApiResponse(responseCode = "401", description = "Unauthorized request", content = @Content(schema = @Schema())),
	})
	public ResponseEntity<GetUserResponseDto> getUser(final JwtAuthenticationToken token) {
		GetUserResponseDto user = userService.getUser(token);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	/**
	 * Login an existing user.
	 *
	 * @param user The login request containing user credentials.
	 * @return ResponseEntity<AuthUserResponseDto> A JWT if login is successful.
	 */
	@PostMapping("/login")
	@Operation(summary = "Login User", description = "Return a token generated from login authentication")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful login user", content = @Content(schema = @Schema(implementation = AuthUserResponseDto.class), examples = @ExampleObject(value = "{\"token\": \"Generated token\"}"), mediaType = "application/json")),
			@ApiResponse(responseCode = "400", description = "Email not found", content = @Content(schema = @Schema(), examples = @ExampleObject(value = "{\"message\": \"Email not found\"}"), mediaType = "application/json")),
			@ApiResponse(responseCode = "401", description = "Unauthorized request", content = @Content(schema = @Schema())),
	})
	public ResponseEntity<AuthUserResponseDto> loginUser(@Valid @RequestBody final UserLoginRequestDto user) {
		AuthUserResponseDto authUserResponse = userService.loginUser(user);
		return new ResponseEntity<>(authUserResponse, HttpStatus.OK);

	}

	/**
	 * Registers a new user.
	 *
	 * @param user The registration request containing user details.
	 * @return ResponseEntity<AuthUserResponseDto> A JWT if registration is successful.
	 */
	@PostMapping("/register")
	@Operation(summary = "Register User", description = "Return a token generated from register user information's")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful register user", content = @Content(schema = @Schema(implementation = AuthUserResponseDto.class), examples = @ExampleObject(value = "{\"token\": \"Generated token\"}"), mediaType = "application/json")),
			@ApiResponse(responseCode = "400", description = "User Already exist", content = @Content(schema = @Schema(), examples = @ExampleObject(value = "{\"message\": \"User Already exist\"}"), mediaType = "application/json")),
			@ApiResponse(responseCode = "401", description = "Unauthorized request", content = @Content(schema = @Schema())),
	})
	public ResponseEntity<AuthUserResponseDto> registerUser(@Valid @RequestBody final UserRegisterRequestDto user) {
		AuthUserResponseDto authUserResponseDto = userService.registerUser(user);
		return new ResponseEntity<>(authUserResponseDto, HttpStatus.OK);
	}

}
