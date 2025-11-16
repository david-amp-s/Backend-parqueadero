package com.parqueadero.parkplace.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TarifaCreateDto(
        @NotBlank(message = "Ingrese un vehiculo valido") String vehiculo,
        @NotNull(message = "Ingrese un valor valido") int nuevaTarifa) {

}
