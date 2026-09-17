package com.myproject.catalogo_productos.dto;

public record AuthResponse(String accessToken, String refreshToken, long expiresIn) {
}
