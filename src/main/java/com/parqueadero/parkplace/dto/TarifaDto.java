package com.parqueadero.parkplace.dto;

import com.parqueadero.parkplace.model.TipoVehiculoEnt;

public record TarifaDto(
        Long id,
        TipoVehiculoEnt tipoVehiculo,
        String tipoCliente,
        int nuevaTarifa) {

}
