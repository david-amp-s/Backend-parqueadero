package com.parqueadero.parkplace.dto;

import jakarta.validation.constraints.NotNull;

public record IngresoCreateDto(
        @NotNull(message = "Ingrese el id del vehiculo") Long vehiculo_id,
        @NotNull(message = "Ingrese el id del espacio") Long espacio_id) {

}
