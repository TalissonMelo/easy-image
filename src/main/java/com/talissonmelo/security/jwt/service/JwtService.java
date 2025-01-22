package com.talissonmelo.security.jwt.service;


import com.talissonmelo.user.controller.response.AccessToken;
import com.talissonmelo.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    public AccessToken generatedToken(User user) {
        return new AccessToken("");
    }
}
