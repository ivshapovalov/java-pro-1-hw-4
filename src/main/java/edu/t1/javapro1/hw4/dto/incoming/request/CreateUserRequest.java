package edu.t1.javapro1.hw4.dto.incoming.request;

import jakarta.validation.constraints.NotEmpty;

public record CreateUserRequest(
        @NotEmpty(message = "Username must not be null")
        String username
) {
}
