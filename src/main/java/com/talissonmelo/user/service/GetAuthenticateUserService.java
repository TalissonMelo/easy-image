package com.talissonmelo.user.service;


import com.talissonmelo.security.jwt.service.JwtService;
import com.talissonmelo.user.controller.response.AccessToken;
import com.talissonmelo.user.domain.User;
import com.talissonmelo.user.repository.UserRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class GetAuthenticateUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public GetAuthenticateUserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AccessToken findByEmailAndPassword(String email, String password) {

        User user = userRepository.findByEmail(email);

        if (user != null) {

            boolean matches = passwordEncoder.matches(password, user.getPassword());

            if (matches) {
                return jwtService.generatedToken(user);
            }
        }

        throw new AccessDeniedException("Incorrect Username or Password");
    }
}
