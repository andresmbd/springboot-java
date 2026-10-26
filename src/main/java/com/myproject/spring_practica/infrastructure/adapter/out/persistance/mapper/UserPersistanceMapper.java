package com.myproject.spring_practica.infrastructure.adapter.out.persistance.mapper;

import org.springframework.stereotype.Component;

import com.myproject.spring_practica.domain.model.User;
import com.myproject.spring_practica.infrastructure.adapter.out.persistance.entity.UserEntity;

@Component 
public class UserPersistanceMapper {

    public UserEntity toEntityModel(User user){
        return UserEntity.builder()
                .id(user.getId())
                .firtName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

     public User toDomainModel(UserEntity userEntity){
        return new User(userEntity.getId(), userEntity.getFirtName(), userEntity.getLastName());
     }
}
