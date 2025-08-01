package com.parqueadero.parkplace.dto;

import jakarta.validation.constraints.NotNull;

public record FacturaCreateDto(
        @NotNull(message = "La salida es obligatoria") Long salida_id,
        @NotNull(message = "El usuario es obligatorio") Long usuario_id,
        @NotNull(message = "El método de pago es obligatorio") Long metodo_pago_id) {

}
