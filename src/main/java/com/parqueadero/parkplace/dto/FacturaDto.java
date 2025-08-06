package com.parqueadero.parkplace.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record FacturaDto(
        Long id,
        Long salida_id,
        Long usuario_id,
        BigDecimal total,
        List<DetallePagoInput> pagos,
        LocalDateTime fecha) {

}
