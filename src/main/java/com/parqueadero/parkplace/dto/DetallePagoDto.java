package com.parqueadero.parkplace.dto;

import java.math.BigDecimal;

public record DetallePagoDto(
        Long id,
        Long factura_id,
        String formaPago,
        BigDecimal monto) {
}