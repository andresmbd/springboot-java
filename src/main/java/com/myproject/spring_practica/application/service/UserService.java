package com.myproject.spring_practica.application.service;

import com.myproject.spring_practica.application.port.in.CreateUserUseCase;
import com.myproject.spring_practica.application.port.in.GetUserUseCase;
import com.myproject.spring_practica.application.port.out.UserRepositoryPort;
import com.myproject.spring_practica.domain.model.User;


public class UserService implements CreateUserUseCase, GetUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public UserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }


    @Override
    public User createUser(User user) {
        return userRepositoryPort.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepositoryPort.findById(id);
    }
}
