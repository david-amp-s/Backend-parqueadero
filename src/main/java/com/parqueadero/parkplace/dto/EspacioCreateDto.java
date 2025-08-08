package com.parqueadero.parkplace.dto;

import com.parqueadero.parkplace.enums.EstadoEspacio;
import com.parqueadero.parkplace.enums.TipoVehiculo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EspacioCreateDto(

        @NotBlank(message = "El código no puede estar vacío") @Size(max = 10, message = "El código no puede tener más de 10 caracteres") String codigo,

        @NotNull(message = "Debe especificar el estado del espacio") EstadoEspacio tipoEspacio,

        @NotNull(message = "Debe especificar el tipo de vehículo permitido") TipoVehiculo tipoVehiculoPermitido

) {
}
