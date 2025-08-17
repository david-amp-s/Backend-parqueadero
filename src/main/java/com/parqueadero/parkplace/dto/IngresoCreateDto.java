package com.parqueadero.parkplace.dto;

import jakarta.validation.constraints.NotBlank;

public record IngresoCreateDto(

        @NotBlank(message = "Ingrese la placa del vehiculo") String placa

) {
}
