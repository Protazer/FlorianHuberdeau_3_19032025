package com.chatop.chaTop.mapper;

import com.chatop.chaTop.model.Message;
import com.chatop.chaTop.payload.request.PostMessageRequestDto;
import com.chatop.chaTop.payload.response.PostMessageResponseDto;
import org.springframework.stereotype.Service;

@Service
public class MessageMapper {
	public PostMessageResponseDto toDtoMessage(Message message) {
		return new PostMessageResponseDto(message.getMessage());
	}

	public Message toEntity(PostMessageRequestDto message) {
		Message NewMessage = new Message();
		NewMessage.setMessage(message.message());
		NewMessage.setUserId(message.user_id());
		NewMessage.setRentalId(message.rental_id());
		return NewMessage;
	}
}
