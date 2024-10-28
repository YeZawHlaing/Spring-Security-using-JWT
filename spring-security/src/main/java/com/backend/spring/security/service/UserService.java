package com.backend.spring.security.service;

import com.backend.spring.security.model.User;
import com.backend.spring.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;
    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(16);

    public User CreateUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public String verifyUser(User user){

        Authentication authentication=authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));

        if(authentication.isAuthenticated()){
            return tokenService.generateToken(user.getUsername());
        }
        return "failed";
    }
}
