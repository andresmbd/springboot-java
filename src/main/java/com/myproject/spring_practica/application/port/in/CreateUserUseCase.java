package com.myproject.spring_practica.application.port.in;

import com.myproject.spring_practica.domain.model.User;

public interface CreateUserUseCase {
    User createUser(User user);
}
