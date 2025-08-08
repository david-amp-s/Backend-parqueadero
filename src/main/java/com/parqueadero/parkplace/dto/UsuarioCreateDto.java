package com.parqueadero.parkplace.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCreateDto(

        @NotBlank(message = "El nombre no puede estar vacío") @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres") String nombre,

        @NotBlank(message = "El correo no puede estar vacío") @Email(message = "Debe ingresar un correo válido") String email,

        @NotBlank(message = "La contraseña no puede estar vacía") @Size(min = 6, max = 100, message = "La contraseña debe tener entre 6 y 100 caracteres") String contraseña,

        @NotBlank(message = "El rol no puede estar vacío") @Size(min = 3, max = 20, message = "El rol debe tener entre 3 y 20 caracteres") String rolNombre

) {
}
