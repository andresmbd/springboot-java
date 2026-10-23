package com.myproject.spring_practica.infrastructure.adapter.out.persistance.repository;

import com.myproject.spring_practica.application.port.out.UserRepositoryPort;
import com.myproject.spring_practica.domain.model.User;
import com.myproject.spring_practica.infrastructure.adapter.out.mapper.UserPersistanceMapper;
import com.myproject.spring_practica.infrastructure.adapter.out.persistance.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class JpaUserRepositoryAdapter implements UserRepositoryPort {
    private final JpaUserRepository userRepository;
    private final UserPersistanceMapper mapper;

    @Override
    public User save(User user) {
        UserEntity userEntity =  mapper.toEntityModel(user);
        userRepository.save(userEntity);
        return mapper.toDomainModel(userEntity);
    }
}
