package com.talissonmelo.user.controller;

import com.talissonmelo.user.controller.request.UserRequest;
import com.talissonmelo.user.domain.User;
import com.talissonmelo.user.domain.UserMapper;
import com.talissonmelo.user.service.CreateUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateUserController {

    private final CreateUserService createUserService;

    public CreateUserController(CreateUserService createUserService) {
        this.createUserService = createUserService;
    }


    @PostMapping("/v1/users")
    public ResponseEntity execute(@Valid @RequestBody UserRequest request) {

        User user = createUserService.save(UserMapper.toUser(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
