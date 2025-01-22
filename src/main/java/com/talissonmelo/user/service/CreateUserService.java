package com.talissonmelo.user.service;


import com.talissonmelo.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateUserService {

    private final UserRepository userRepository;
    private final GetUserByEmailService getUserByEmailService;

    public CreateUserService(UserRepository userRepository, GetUserByEmailService getUserByEmailService) {
        this.userRepository = userRepository;
        this.getUserByEmailService = getUserByEmailService;
    }
}
