package com.parqueadero.parkplace.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parqueadero.parkplace.model.Usuario;
import com.parqueadero.parkplace.security.dto.AuthDto;
import com.parqueadero.parkplace.security.dto.RespuestaLoginDto;
import com.parqueadero.parkplace.security.service.TokenService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager manager;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid AuthDto datos) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(datos.email(), datos.contraseña());
        var autenticacion = manager.authenticate(authenticationToken);

        var usuario = (Usuario) autenticacion.getPrincipal();
        var tokenJwt = tokenService.generarToken((Usuario) autenticacion.getPrincipal());
        return ResponseEntity.ok(new RespuestaLoginDto(tokenJwt, usuario.getId(), usuario.getRol().getNombre()));
    }

}
