package com.myproject.catalogo_productos.controller;

import com.myproject.catalogo_productos.dto.AuthResponse;
import com.myproject.catalogo_productos.dto.LoginRequest;
import com.myproject.catalogo_productos.dto.RefreshRequest;
import com.myproject.catalogo_productos.entity.RefreshToken;
import com.myproject.catalogo_productos.security.JwtService;
import com.myproject.catalogo_productos.service.RefreshTokenService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, RefreshTokenService refreshTokenService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.refreshTokenService = refreshTokenService;
        this.jwtService = jwtService;
    }

    private AuthResponse issueTokens(String username){
        String access = jwtService.generateAccessToken(username);
        RefreshToken refresh = refreshTokenService.create(username);
        return new AuthResponse(access, refresh.getToken(), jwtService.getAccessTokenExpiration());
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        return issueTokens(request.username());
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@Valid @RequestBody RefreshRequest request) {
        RefreshToken current = refreshTokenService.validate(request.refreshToken());
        refreshTokenService.revoke(current);
        return issueTokens(current.getUsername());
    }

}
