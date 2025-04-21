package com.chatop.chaTop.controller;

import com.chatop.chaTop.payload.request.PostMessageRequestDto;
import com.chatop.chaTop.payload.response.PostMessageResponseDto;
import com.chatop.chaTop.service.MessageService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Post a rental Message")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message send with success", content = @Content(schema = @Schema(implementation = PostMessageResponseDto.class), mediaType = "application/json", examples = @ExampleObject(value = "{\"message\":\"Message send with success\"}"))),
            @ApiResponse(responseCode = "401", description = "Unauthorized request", content = @Content(schema = @Schema())),
    })
    public ResponseEntity<PostMessageResponseDto> postMessage(@Valid @RequestBody PostMessageRequestDto request) {
        PostMessageResponseDto postMessageResponse = messageService.postMessage(request);
        return new ResponseEntity<PostMessageResponseDto>(postMessageResponse, HttpStatus.OK);
    }
}

