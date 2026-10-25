package com.myproject.spring_practica.infrastructure.adapter.out.persistance.repository;
import com.myproject.spring_practica.infrastructure.adapter.out.persistance.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

}
