package com.parqueadero.parkplace.dto;

import jakarta.validation.constraints.NotBlank;

public record SalidaCreateDto(

        @NotBlank(message = "La placa no puede estar vacia") String placa

) {
}
