package br.com.tirolezo.imeatapi.domain.user;

import jakarta.validation.constraints.Email;

public record RegisterDTO(
        String login,
        @Email
        String email,
        String password,
        UserRole role) {
}
