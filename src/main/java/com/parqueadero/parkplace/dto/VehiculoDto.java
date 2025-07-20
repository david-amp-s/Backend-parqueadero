package com.parqueadero.parkplace.dto;

import com.parqueadero.parkplace.enums.TipoVehiculo;

public record VehiculoDto(Long id, String placa, TipoVehiculo tipoVehiculo, ClienteResponsiveDto cliente) {

}
