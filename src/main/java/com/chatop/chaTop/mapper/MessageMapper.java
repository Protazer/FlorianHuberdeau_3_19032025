package com.chatop.chaTop.mapper;

import com.chatop.chaTop.model.Message;
import com.chatop.chaTop.payload.request.PostMessageRequestDto;
import com.chatop.chaTop.payload.response.PostMessageResponseDto;
import org.springframework.stereotype.Service;

/**
 * This class represents a mapper for converting Message Entity to
 * PostMessageResponseDto objects and PostMessageResponseDto to Message Entity.
 */
@Service
public class MessageMapper {

    /**
     * Converts a Message to PostMessageResponseDto objects.
     *
     * @param message The Message to be converted.
     * @return The PostMessageResponseDto objects.
     */
    public PostMessageResponseDto toDtoMessage(final Message message) {
        return new PostMessageResponseDto(message.getMessage());
    }

    /**
     * Converts a PostMessageResponseDto to Message Entity.
     *
     * @param message The PostMessageResponseDto to be converted.
     * @return The Message Entity.
     */
    public Message toEntity(final PostMessageRequestDto message) {
        Message NewMessage = new Message();
        NewMessage.setMessage(message.message());
        NewMessage.setUserId(message.user_id());
        NewMessage.setRentalId(message.rental_id());
        return NewMessage;
    }
}
