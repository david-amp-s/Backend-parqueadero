package com.parqueadero.parkplace.dto;

import com.parqueadero.parkplace.enums.TipoVehiculo;

import jakarta.validation.constraints.NotNull;

public record TarifaCreateDto(
        @NotNull(message = "Ingrese un vehiculo valido") TipoVehiculo tipoVehiculo,
        @NotNull(message = "Ingrese un valor valido") int nuevaTarifa) {

}
