package com.chatop.chaTop.mapper;

import com.chatop.chaTop.dto.MessageDto;
import com.chatop.chaTop.model.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageMapper {
	public MessageDto toDto(Message message) {
		return new MessageDto(message.getMessage(), message.getUserId(), message.getRentalId());
	}
}
