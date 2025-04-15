package com.chatop.chaTop.controller;

import com.chatop.chaTop.payload.request.UserLoginRequestDto;
import com.chatop.chaTop.payload.request.UserRegisterRequestDto;
import com.chatop.chaTop.payload.response.AuthUserResponseDto;
import com.chatop.chaTop.payload.response.GetUserResponseDto;
import com.chatop.chaTop.service.JWTService;
import com.chatop.chaTop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class UserController {

    private final UserService userService;

    private final JWTService jwtService;

    public UserController(final UserService userService, final JWTService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
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
