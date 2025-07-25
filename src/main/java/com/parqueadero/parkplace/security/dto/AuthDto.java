package com.parqueadero.parkplace.security.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthDto(
                @NotBlank(message = "El correo no puede estar vacio") String email,
                @NotBlank(message = "La contraseña no puede estar vacia") String contraseña) {

}
