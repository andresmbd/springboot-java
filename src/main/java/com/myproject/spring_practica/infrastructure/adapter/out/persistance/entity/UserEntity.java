package com.myproject.spring_practica.infrastructure.adapter.out.persistance.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NegativeOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter @Builder @AllArgsConstructor @NoArgsConstructor
@Table(name = "usuarios")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firtName;
    private String lastName;

}
