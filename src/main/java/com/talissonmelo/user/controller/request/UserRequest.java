package com.talissonmelo.user.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(@Email String email, @NotBlank String name, @NotBlank String password) {
}
