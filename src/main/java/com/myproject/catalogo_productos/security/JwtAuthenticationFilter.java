package com.myproject.catalogo_productos.security;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        System.out.println(">>> JWT FILTER");
        System.out.println(">>> METHOD: " + request.getMethod());
        System.out.println(">>> URL: " + request.getRequestURI());
        System.out.println(">>> Authorization: " + header);

        if (header == null || !header.startsWith("Bearer ")) {
            System.out.println(">>> NO HAY BEARER TOKEN");
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring("Bearer ".length());

        try {
            String username = jwtService.extractUsername(token);

            System.out.println(">>> USERNAME DEL TOKEN: " + username);

            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails user = userDetailsService.loadUserByUsername(username);

                System.out.println(">>> USUARIO ENCONTRADO: " + user.getUsername());

                if (jwtService.isValid(token, user)) {
                    System.out.println(">>> JWT VALIDO");
                    var authentication = new UsernamePasswordAuthenticationToken(
                            user, null, user.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println(">>> AUTENTICACION ESTABLECIDA");
                }else {
                    System.out.println(">>> JWT INVALIDO");
                }
            }
        } catch (RuntimeException e) { // catch (RuntimeException ignored) {}
            System.out.println(">>> ERROR PROCESANDO JWT");
            e.printStackTrace();
        }
        filterChain.doFilter(request, response);
    }
}
