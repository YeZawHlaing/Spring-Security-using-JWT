package com.backend.spring.security.service;

import com.backend.spring.security.model.User;
import com.backend.spring.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User CreateUser(User user) {
        return userRepository.save(user);
    }


    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
