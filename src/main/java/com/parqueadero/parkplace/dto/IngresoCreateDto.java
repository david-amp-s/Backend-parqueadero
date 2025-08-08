package com.parqueadero.parkplace.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record IngresoCreateDto(

                @NotNull(message = "Ingrese el id del vehículo") @Positive(message = "El id del vehículo debe ser un número positivo") Long vehiculo_id,

                @NotNull(message = "Ingrese el id del espacio") @Positive(message = "El id del espacio debe ser un número positivo") Long espacio_id

) {
}
