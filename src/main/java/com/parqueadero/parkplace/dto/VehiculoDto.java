package com.parqueadero.parkplace.dto;

import com.parqueadero.parkplace.model.TipoVehiculoEnt;

public record VehiculoDto(Long id, String placa, String tipoVehiculo, ClienteResponsiveDto cliente) {

}
