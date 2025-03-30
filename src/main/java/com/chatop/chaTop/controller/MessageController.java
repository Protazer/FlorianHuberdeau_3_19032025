package com.chatop.chaTop.controller;

import com.chatop.chaTop.payload.request.PostMessage;
import com.chatop.chaTop.payload.response.PostMessageResponse;
import com.chatop.chaTop.service.MessageService;
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
    public ResponseEntity<PostMessageResponse> postMessage(@RequestBody PostMessage request) {
        try {
            PostMessageResponse PostMessageResponse = messageService.postMessage(request);
            return new ResponseEntity<PostMessageResponse>(PostMessageResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
