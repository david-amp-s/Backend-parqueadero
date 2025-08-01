package com.parqueadero.parkplace.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FacturaDto(
        Long id,
        Long salida_id,
        Long usuario_id,
        BigDecimal total,
        String metodo_pago_id,
        LocalDateTime fecha) {

}
