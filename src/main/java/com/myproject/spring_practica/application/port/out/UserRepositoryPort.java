package com.myproject.spring_practica.application.port.out;

import com.myproject.spring_practica.domain.model.User;

import java.util.Optional;

public interface UserRepositoryPort {
    User save(User user);
    User findById(Long id);
}
