package com.myproject.spring_practica.infrastructure.adapter.in.web.controller;

import com.myproject.spring_practica.application.port.in.CreateUserUseCase;
import com.myproject.spring_practica.application.port.in.GetUserUseCase;
import com.myproject.spring_practica.domain.model.User;
import com.myproject.spring_practica.infrastructure.adapter.in.web.dto.request.CreateUserRequest;
import com.myproject.spring_practica.infrastructure.adapter.in.web.dto.response.UserResponse;
import com.myproject.spring_practica.infrastructure.adapter.in.web.mapper.UserWebMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController @AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final UserWebMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody CreateUserRequest createUserRequest){
        User user = mapper.toDomainModel(createUserRequest);
        createUserUseCase.createUser(user);
        return mapper.toResponseDto(user);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserById(@PathVariable("id") Long id){
        User user = getUserUseCase.findById(id);
        return mapper.toResponseDto(user);
    }
}
