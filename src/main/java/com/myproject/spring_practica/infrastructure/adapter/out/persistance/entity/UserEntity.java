package com.myproject.spring_practica.infrastructure.adapter.out.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity @Getter @AllArgsConstructor
@Table(name = "usuarios")
public final class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    private final String firtName;
    private final String lastName;

}
