package com.parqueadero.parkplace.dto;

import java.time.LocalDateTime;

public record IngresoDto(
        Long id,
        Long vehiculo,
        Long espacio,
        LocalDateTime fecha_ingreso) {

}
