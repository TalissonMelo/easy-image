package com.talissonmelo.user.service;


import com.talissonmelo.user.domain.User;
import com.talissonmelo.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateUserService {

    private final UserRepository userRepository;
    private final GetUserByEmailService getUserByEmailService;

    private final PasswordEncoder passwordEncoder;

    public CreateUserService(UserRepository userRepository, GetUserByEmailService getUserByEmailService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.getUserByEmailService = getUserByEmailService;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(User user) {
        User existUserByEmail = getUserByEmailService.findByEmail(user.getEmail());

        if (existUserByEmail != null) {
            throw new IllegalArgumentException("User already exists by email: " + user.getEmail());
        }

        user.setName(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }
}
