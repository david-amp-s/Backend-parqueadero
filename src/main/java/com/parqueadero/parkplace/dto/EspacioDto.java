package com.parqueadero.parkplace.dto;

import com.parqueadero.parkplace.enums.EstadoEspacio;
import com.parqueadero.parkplace.enums.TipoVehiculo;

public record EspacioDto(
        Long id,
        String codigo,
        EstadoEspacio tipoEspacio,
        TipoVehiculo tipoVehiculoPermitido) {

}
