package com.parqueadero.parkplace.dto;

import java.math.BigDecimal;

public record DetallePagoInput(
                Long formaPagoId,
                BigDecimal pago) {

}
