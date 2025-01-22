package com.talissonmelo.user.service;


import com.talissonmelo.user.domain.User;
import com.talissonmelo.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class GetUserByEmailService {

    private final UserRepository userRepository;

    public GetUserByEmailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
