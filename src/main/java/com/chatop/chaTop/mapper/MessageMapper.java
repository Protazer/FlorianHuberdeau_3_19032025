package com.chatop.chaTop.mapper;

import com.chatop.chaTop.model.Message;
import com.chatop.chaTop.payload.request.PostMessage;
import com.chatop.chaTop.payload.response.PostMessageResponse;
import org.springframework.stereotype.Service;

@Service
public class MessageMapper {
    public PostMessageResponse toDtoMessage(Message message) {
        return new PostMessageResponse(message.getMessage());
    }

    public Message toEntity(PostMessage message) {
        Message NewMessage = new Message();
        NewMessage.setMessage(message.message());
        NewMessage.setUserId(message.user_id());
        NewMessage.setRentalId(message.rental_id());
        return NewMessage;
    }
}
