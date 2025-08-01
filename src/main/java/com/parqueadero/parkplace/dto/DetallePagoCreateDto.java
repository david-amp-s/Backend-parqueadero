package com.parqueadero.parkplace.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public record DetallePagoCreateDto(
        @NotNull(message = "Debe especificar la factura") Long factura_id,
        @NotNull(message = "Debe especificar el método de pago") Long formaPago_id,
        @NotNull(message = "Debe ingresar el monto pagado") BigDecimal monto) {
}
