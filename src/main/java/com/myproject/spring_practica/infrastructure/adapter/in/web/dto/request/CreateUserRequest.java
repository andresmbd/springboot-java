package com.myproject.spring_practica.infrastructure.adapter.in.web.dto.request;

public record CreateUserRequest(
        Long id,
        String fistName,
        String lastName
) {
}
