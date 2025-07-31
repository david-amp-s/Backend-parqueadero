package com.parqueadero.parkplace.dto;

import java.time.LocalDateTime;

public record SalidaDto(
        Long id,
        Long ingreso_id,
        LocalDateTime fechaSalida,
        int total) {

}
