package com.myproject.spring_practica.infrastructure.config;

import com.myproject.spring_practica.application.port.in.CreateUserUseCase;
import com.myproject.spring_practica.application.port.in.GetUserUseCase;
import com.myproject.spring_practica.application.port.out.UserRepositoryPort;
import com.myproject.spring_practica.application.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * config existe porque la capa de aplicación es 100% pura, y el
 * framework necesita un lugar (fuera del dominio y de la aplicación)
 * para enterarse de cómo unir las piezas y crear los beans.
 */



@Configuration
public class BeanConfiguration {

    @Bean
    public CreateUserUseCase createUserUseCase(UserRepositoryPort userRepositoryPort) {
        return new UserService(userRepositoryPort);
    }

    @Bean
    public GetUserUseCase getUserUseCase(UserRepositoryPort userRepositoryPort){
        return new UserService(userRepositoryPort);
    }
}
