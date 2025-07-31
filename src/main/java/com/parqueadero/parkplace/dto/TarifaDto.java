package com.parqueadero.parkplace.dto;

import com.parqueadero.parkplace.enums.TipoVehiculo;

public record TarifaDto(
        Long id,
        TipoVehiculo tipoVehiculo,
        int nuevaTarifa) {

}
