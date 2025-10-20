package com.parqueadero.parkplace.dto;

import java.time.LocalDateTime;

public record SalidaDto(
        Long id,
        LocalDateTime ingreso_Id,
        LocalDateTime fechaSalida,
        int total) {

}
