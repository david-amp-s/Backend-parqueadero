package com.parqueadero.parkplace.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioCreateDto(
                @NotBlank(message = "El nombre no puede estar vacio") String nombre,
                @NotBlank(message = "El correo no puede estar vacio") String email,
                @NotBlank(message = "La contraseña no puede estar vacia") String contraseña,
                @NotBlank(message = "El rol no puede estar vacio") String rolNombre) {

}
