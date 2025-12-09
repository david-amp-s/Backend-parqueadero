package com.parqueadero.parkplace.dto;

public record EspacioDto(
        Long id,
        String codigo,
        String tipoEspacio,
        String tipoVehiculoPermitido) {

}
