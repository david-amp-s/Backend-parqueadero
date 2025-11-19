package com.parqueadero.parkplace.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteCreateDto(
                @NotBlank(message = "El nombre no puede estar vacio") String nombre,
                @NotBlank(message = "El correo no puede estar vacío") @Email(message = "Tiene que tener formato de correo") String correo,
                @NotBlank(message = "El campo de cedula no puede estar vacia") String cedula,
                String tipoCliente) {

}
