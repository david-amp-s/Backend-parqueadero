package com.parqueadero.parkplace.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SalidaCreateDto(

                @NotNull(message = "Debe ingresar el id del ingreso") @Positive(message = "El id del ingreso debe ser un número positivo") Long ingreso_id

) {
}
