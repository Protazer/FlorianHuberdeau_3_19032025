package com.chatop.chaTop.service;

import com.chatop.chaTop.model.Message;
import com.chatop.chaTop.repository.MessageRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(final MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message postMessage(Message message) {
        return messageRepository.save(message);
    }
}
