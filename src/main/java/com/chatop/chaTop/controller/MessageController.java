package com.chatop.chaTop.controller;

import com.chatop.chaTop.payload.request.PostMessageRequestDto;
import com.chatop.chaTop.payload.response.PostMessageResponseDto;
import com.chatop.chaTop.service.MessageService;
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
	public ResponseEntity<PostMessageResponseDto> postMessage(@Valid @RequestBody PostMessageRequestDto request) {
		PostMessageResponseDto PostMessageResponse = messageService.postMessage(request);
		return new ResponseEntity<PostMessageResponseDto>(PostMessageResponse, HttpStatus.OK);
	}
}
