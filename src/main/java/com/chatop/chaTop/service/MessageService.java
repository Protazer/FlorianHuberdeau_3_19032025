package com.chatop.chaTop.service;

import com.chatop.chaTop.mapper.MessageMapper;
import com.chatop.chaTop.model.Message;
import com.chatop.chaTop.payload.request.PostMessageRequestDto;
import com.chatop.chaTop.payload.response.PostMessageResponseDto;
import com.chatop.chaTop.repository.MessageRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class MessageService {

	private final MessageRepository messageRepository;
	private final MessageMapper messageMapper;

	public MessageService(final MessageRepository messageRepository, final MessageMapper messageMapper) {
		this.messageRepository = messageRepository;
		this.messageMapper = messageMapper;
	}

	public PostMessageResponseDto postMessage(PostMessageRequestDto message) {
		Message newMessage = messageMapper.toEntity(message);
		messageRepository.save(newMessage);
		return new PostMessageResponseDto("Message send with success");
	}
}
