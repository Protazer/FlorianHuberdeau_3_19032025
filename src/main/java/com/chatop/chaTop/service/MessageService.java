package com.chatop.chaTop.service;

import com.chatop.chaTop.model.Message;
import com.chatop.chaTop.repository.MessageRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message postMessage(Message message) {
        return messageRepository.save(message);
    }
}
