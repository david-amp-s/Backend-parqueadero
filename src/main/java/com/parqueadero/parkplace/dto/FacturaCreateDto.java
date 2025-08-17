package com.parqueadero.parkplace.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record FacturaCreateDto(

        @NotBlank(message = "Ingrese una placa") String placa,

        @NotNull(message = "El usuario es obligatorio") Long usuario_id,

        @NotEmpty(message = "Debe indicar al menos un pago") @Valid

        List<DetallePagoInput> pagos

) {
}
