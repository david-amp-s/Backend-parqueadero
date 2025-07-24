package com.parqueadero.parkplace.security.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.parqueadero.parkplace.model.Usuario;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {
    @Value("${app.security.jwt.secret}")
    private String jwtSecret;

    public String generarToken(Usuario usuario) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            return JWT.create()
                    .withIssuer("parkplace-backend")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(fechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error al generar el token jwt", exception);
        }
    }

    private Instant fechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
