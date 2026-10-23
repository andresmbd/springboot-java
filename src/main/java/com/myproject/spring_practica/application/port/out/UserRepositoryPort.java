package com.myproject.spring_practica.application.port.out;

import com.myproject.spring_practica.domain.model.User;

public interface UserRepositoryPort {
    User save(User user);
}
