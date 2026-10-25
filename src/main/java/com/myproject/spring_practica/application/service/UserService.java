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

//    private User validateUser(User user){
//        if (user == null ) throw new InvalidEnterDataException("El Usuario no puede ser nulo");
//        if(user.getId() == null || user.getId().equals())
//        if (user.getFirstName() == null || user.getFirstName().trim().isBlank())
//            throw new InvalidEnterDataException("El nombre no puede estar vacio");
//
//    }

    @Override
    public User createUser(User user) {
        return userRepositoryPort.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepositoryPort.findById(id);
    }
}
