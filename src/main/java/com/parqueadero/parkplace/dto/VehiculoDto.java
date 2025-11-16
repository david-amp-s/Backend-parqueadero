package com.parqueadero.parkplace.dto;

import com.parqueadero.parkplace.model.TipoVehiculoEnt;

public record VehiculoDto(Long id, String placa, TipoVehiculoEnt tipoVehiculo, ClienteResponsiveDto cliente) {

}
