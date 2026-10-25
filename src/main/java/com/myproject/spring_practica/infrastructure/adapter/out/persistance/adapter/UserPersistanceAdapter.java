package com.myproject.spring_practica.infrastructure.adapter.out.persistance.adapter;

import com.myproject.spring_practica.application.port.out.UserRepositoryPort;
import com.myproject.spring_practica.domain.exception.ResourceNotFoundException;
import com.myproject.spring_practica.domain.model.User;
import com.myproject.spring_practica.infrastructure.adapter.out.persistance.mapper.UserPersistanceMapper;
import com.myproject.spring_practica.infrastructure.adapter.out.persistance.entity.UserEntity;
import com.myproject.spring_practica.infrastructure.adapter.out.persistance.repository.JpaUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@AllArgsConstructor
@Repository
public class UserPersistanceAdapter implements UserRepositoryPort {
    private final JpaUserRepository userRepository;
    private final UserPersistanceMapper mapper;

    @Override
    public User save(User user) {
        UserEntity userEntity =  mapper.toEntityModel(user);
        userRepository.save(userEntity);
        return mapper.toDomainModel(userEntity);
    }

    @Override
    public User findById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No se encuentra el id"+id));
        return mapper.toDomainModel(userEntity);
    }
}
