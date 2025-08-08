package com.parqueadero.parkplace.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record FacturaCreateDto(

                @NotNull(message = "La salida es obligatoria") Long salida_id,

                @NotNull(message = "El usuario es obligatorio") Long usuario_id,

                @NotEmpty(message = "Debe indicar al menos un pago") @Valid

                List<DetallePagoInput> pagos

) {
}
