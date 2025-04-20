package com.chatop.chaTop.controller;

import com.chatop.chaTop.payload.request.UserLoginRequestDto;
import com.chatop.chaTop.payload.request.UserRegisterRequestDto;
import com.chatop.chaTop.payload.response.AuthUserResponseDto;
import com.chatop.chaTop.payload.response.GetUserResponseDto;
import com.chatop.chaTop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final UserService userService;


    public AuthController(final UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/me")
    @Operation(summary = "Get User by token", description = "Return a user as per the token")
    @SecurityRequirement(name = "Bearer Authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Succesfull retrived user", content = @Content(schema = @Schema(implementation = GetUserResponseDto.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unauthorized request", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<GetUserResponseDto> getUser(JwtAuthenticationToken token) {
        GetUserResponseDto user = userService.getUser(token);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PostMapping("/login")
    @Operation(summary = "Login User", description = "Return a token generated from login authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Succesfull login user", content = @Content(schema = @Schema(implementation = AuthUserResponseDto.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unauthorized request", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "400", description = "Invalid Email/Password", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "400", description = "Email not found", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<AuthUserResponseDto> loginUser(@Valid @RequestBody UserLoginRequestDto user) {
        AuthUserResponseDto authUserResponse = userService.loginUser(user);
        return new ResponseEntity<>(authUserResponse, HttpStatus.OK);

    }

    @PostMapping("/register")
    @Operation(summary = "Register User", description = "Return a token generated from register user information's")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Succesfull register user", content = @Content(schema = @Schema(implementation = AuthUserResponseDto.class), mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unauthorized request", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "400", description = "User Already exist", content = @Content(schema = @Schema()))
    })
    public ResponseEntity<AuthUserResponseDto> registerUser(@Valid @RequestBody UserRegisterRequestDto user) {
        AuthUserResponseDto authUserResponseDto = userService.registerUser(user);
        return new ResponseEntity<>(authUserResponseDto, HttpStatus.OK);
    }

}
