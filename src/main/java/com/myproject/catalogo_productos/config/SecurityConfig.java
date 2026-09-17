package com.myproject.catalogo_productos.config;

import com.myproject.catalogo_productos.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain; // definir un Bean que configura todas las reglas de acceso y protección HTTP de tu aplicación.

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    SecurityFilterChain securityFilterChain(HttpSecurityBuilder http, JwtAuthenticationFilter jwtFilter) throws Exception{

    }
}
