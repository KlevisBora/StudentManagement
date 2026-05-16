package com.studentmanagementspringboot.service;

import com.studentmanagementspringboot.dto.LoginRequestDTO;
import com.studentmanagementspringboot.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(LoginRequestDTO credentials) {
        return userRepository.findByUsernameAndPassword(credentials.getUsername(), credentials.getPassword()).isPresent();
    }
}
