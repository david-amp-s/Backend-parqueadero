package com.parqueadero.parkplace.dto;

import jakarta.validation.constraints.NotBlank;

public record FormaPagoCreateDto(
        @NotBlank(message = "Ingresar detalle del pago") String detalle) {

}
