package com.talissonmelo.user.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserAuthenticateRequest(@Email String email, @NotBlank String password) {
}
