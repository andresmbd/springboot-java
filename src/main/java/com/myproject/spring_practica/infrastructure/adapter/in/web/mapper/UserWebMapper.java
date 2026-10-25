package com.myproject.spring_practica.infrastructure.adapter.in.web.mapper;

import com.myproject.spring_practica.domain.model.User;
import com.myproject.spring_practica.infrastructure.adapter.in.web.dto.request.CreateUserRequest;
import com.myproject.spring_practica.infrastructure.adapter.in.web.dto.response.UserResponse;
import com.myproject.spring_practica.infrastructure.adapter.out.persistance.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserWebMapper {

    public User toDomainModel(CreateUserRequest createUserRequest){
        return new User(createUserRequest.id(), createUserRequest.fistName(), createUserRequest.lastName() );
    }

    public UserResponse toResponseDto(User user){
        return new UserResponse(user.getId(),user.getFirstName(),user.getLastName());
    }
}
