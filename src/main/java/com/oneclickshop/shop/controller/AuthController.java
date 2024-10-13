package com.oneclickshop.shop.controller;

import com.oneclickshop.shop.model.User;
import com.oneclickshop.shop.model.UserModel;
import com.oneclickshop.shop.repository.UserRepository;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final UserRepository userRepository;

    @PostMapping("/login")
    public String login( @Validated  @RequestBody  UserModel loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if(Objects.isNull(user)){
            log.info("User info: {}",loginRequest);
            User user1 = new User();
            user1.setRole(UUID.randomUUID().toString());
            user1.setUsername(loginRequest.getUsername());
            user1.setPassword(loginRequest.getPassword());
            user1.setRole(loginRequest.getRole());
            userRepository.save(user1);
            return "New user added";
        }
        else if(user.getPassword().equals(loginRequest.getPassword())) {
            return "Logged in as " + user.getRole();
        }
        return "Invalid credentials";
    }
}