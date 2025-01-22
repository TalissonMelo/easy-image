package com.talissonmelo.user.domain;

import com.talissonmelo.user.controller.request.UserRequest;

public class UserMapper {

    public static User toUser(UserRequest request) {
        return User.to(request.name(), request.email(), request.password());
    }
}
