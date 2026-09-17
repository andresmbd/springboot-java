package com.myproject.catalogo_productos.service;

import com.myproject.catalogo_productos.entity.RefreshToken;
import com.myproject.catalogo_productos.exception.InvalidDataException;
import com.myproject.catalogo_productos.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository repository;
    private final long refreshTokenExpiration;

    public RefreshTokenService(RefreshTokenRepository repository,
                               @Value("${app.security.refresh-token-expiration-ms:604800000}") long refreshTokenExpiration) {
        this.repository = repository;
        this.refreshTokenExpiration = refreshTokenExpiration;
    }

    public RefreshToken create(String username) {
        RefreshToken refreshToken = new RefreshToken(
                UUID.randomUUID().toString(),
                username,
                Instant.now().plusMillis(refreshTokenExpiration),
                false);
        return repository.save(refreshToken);
    }

    public RefreshToken validate(String token) {
        RefreshToken refreshToken = repository.findById(token)
                .orElseThrow(this::invalidToken);
        if (refreshToken.isRevoked() || refreshToken.getExpiresAt().isBefore(Instant.now())) {
            throw invalidToken();
        }
        return refreshToken;
    }

    @Transactional
    public void revoke(RefreshToken token) {
        token.setRevoked(true);
        repository.save(token);
    }

    private InvalidDataException invalidToken() {
        return new InvalidDataException("Refresh token inválido o expirado");
    }

}
