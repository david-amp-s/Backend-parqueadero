package com.parqueadero.parkplace.dto;

import com.parqueadero.parkplace.enums.TipoVehiculo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record VehiculoCreateDto(

        @NotBlank(message = "La placa no puede estar vacía") @Size(min = 6, max = 7, message = "La placa debe tener entre 6 y 7 caracteres") @Pattern(regexp = "^[A-Z0-9]+$", message = "La placa solo puede contener letras mayúsculas y números") String placa,

        @NotNull(message = "Debe especificar el tipo de vehículo") TipoVehiculo tipoVehiculo,

        @NotBlank(message = "La cedula no puede estar vacia") String cedula) {
}
