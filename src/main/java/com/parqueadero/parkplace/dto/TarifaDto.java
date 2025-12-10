package com.parqueadero.parkplace.dto;

import com.parqueadero.parkplace.model.TipoVehiculoEnt;

public record TarifaDto(
                Long id,
                String tipoVehiculo,
                String tipoCliente,
                Integer nuevaTarifa) {

}
