package com.backend.spring.security.controller;


import com.backend.spring.security.model.User;
import com.backend.spring.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@CrossOrigin
@RequiredArgsConstructor

public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/Register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.CreateUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    @GetMapping("/getUser")
    public List<User> getAllUser(){
        return userService.getAllUser();

    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        return userService.verifyUser(user) ;
    }
}
