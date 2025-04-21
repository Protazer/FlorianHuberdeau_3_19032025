package com.chatop.chaTop.controller;

import com.chatop.chaTop.payload.response.GetUserResponseDto;
import com.chatop.chaTop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling user-related operations.
 */
@RestController
@RequestMapping("api/user")
public class UserController {

	private final UserService userService;

	public UserController(final UserService userService) {
		this.userService = userService;
	}

	/**
	 * Retrieves information about a specific user.
	 *
	 * @param id The ID of the user to retrieve.
	 * @return ResponseEntity<GetUserResponseDto> with user information.
	 */
	@GetMapping("/{id}")
	@Operation(summary = "Get User by id", description = "Return a user as per the id")
	@SecurityRequirement(name = "Bearer Authentication")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Succesfull retrived user", content = @Content(schema = @Schema(implementation = GetUserResponseDto.class), examples = @ExampleObject(value = "{\n" +
					"  \"id\": 1,\n" +
					"\"name\": \"User Name\",\n" +
					"\"email\": \"test@test.com\",\n" +
					"\"created_at\": \"2022/02/02\",\n" +
					"\"updated_at\": \"2022/08/02\"  \n" +
					"}"), mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(), examples = @ExampleObject(value = "{\"message\":\"User not found\"}"), mediaType = "application/json")),
			@ApiResponse(responseCode = "401", description = "Unauthorized request", content = @Content(schema = @Schema())),
	})
	public ResponseEntity<GetUserResponseDto> getUserById(@PathVariable final int id) {
		GetUserResponseDto user = userService.getUserById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
