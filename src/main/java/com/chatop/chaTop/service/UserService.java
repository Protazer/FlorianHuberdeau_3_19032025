package com.chatop.chaTop.service;

import com.chatop.chaTop.repository.UserRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
