package com.parqueadero.parkplace.dto;

import com.parqueadero.parkplace.enums.TipoVehiculo;
import com.parqueadero.parkplace.model.Cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VehiculoCreateDto(
        @NotBlank(message = "La placa no puede estar vacia") String placa,
        @NotNull(message = "Debe de especificar el tipo de vehiculo") TipoVehiculo tipoVehiculo,
        @NotNull(message = "El cliente no puede ser nulo") Cliente cliente

) {

}
