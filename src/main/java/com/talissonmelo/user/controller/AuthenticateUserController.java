package com.talissonmelo.user.controller;

import com.talissonmelo.user.controller.request.UserAuthenticateRequest;
import com.talissonmelo.user.controller.response.AccessToken;
import com.talissonmelo.user.service.GetAuthenticateUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticateUserController {

    private final GetAuthenticateUserService getAuthenticateUserService;

    public AuthenticateUserController(GetAuthenticateUserService getAuthenticateUserService) {
        this.getAuthenticateUserService = getAuthenticateUserService;
    }


    @PostMapping("/v1/users")
    public ResponseEntity execute(@Valid @RequestBody UserAuthenticateRequest request) {

        AccessToken accessToken = getAuthenticateUserService.findByEmailAndPassword(request.email(), request.password());
        return ResponseEntity.status(HttpStatus.CREATED).body(accessToken);
    }
}
