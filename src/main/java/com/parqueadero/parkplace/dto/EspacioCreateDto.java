package com.parqueadero.parkplace.dto;

import com.parqueadero.parkplace.enums.EstadoEspacio;
import com.parqueadero.parkplace.enums.TipoVehiculo;

import jakarta.validation.constraints.NotBlank;

public record EspacioCreateDto(
        @NotBlank(message = "No puede ser un dato en blanco") String codigo,
        @NotBlank(message = "Tiene que tener un tipo de estado") EstadoEspacio tipoEspacio,
        @NotBlank(message = "Debe de registrar un tipo de vehiculo") TipoVehiculo tipoVehiculoPermitido

) {

}
