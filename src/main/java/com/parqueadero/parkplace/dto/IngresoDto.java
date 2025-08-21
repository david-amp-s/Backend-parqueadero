package com.parqueadero.parkplace.dto;

import java.time.LocalDateTime;

public record IngresoDto(
                Long id,
                String placa,
                String tipoVehiculo,
                String espacio,
                LocalDateTime fecha_ingreso) {

}
