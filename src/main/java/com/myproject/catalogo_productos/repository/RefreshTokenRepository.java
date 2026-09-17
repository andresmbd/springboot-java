package com.myproject.catalogo_productos.repository;

import com.myproject.catalogo_productos.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
}
