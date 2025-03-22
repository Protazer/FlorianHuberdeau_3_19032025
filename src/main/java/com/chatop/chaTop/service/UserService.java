package com.chatop.chaTop.service;

import com.chatop.chaTop.repository.UserRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class UserService {

    private UserRepository userRepository;
}
