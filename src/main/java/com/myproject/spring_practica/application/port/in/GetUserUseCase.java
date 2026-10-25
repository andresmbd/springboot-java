package com.myproject.spring_practica.application.port.in;

import com.myproject.spring_practica.domain.model.User;

import java.util.Optional;

public interface GetUserUseCase {
    User findById(Long id);
}
