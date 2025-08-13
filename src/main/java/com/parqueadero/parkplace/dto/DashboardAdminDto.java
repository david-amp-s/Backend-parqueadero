package com.parqueadero.parkplace.dto;

import java.math.BigDecimal;

public record DashboardAdminDto(
        Integer totalVehiculos,
        Integer totalSalidas,
        Integer espaciosDisponibles,
        BigDecimal diario,
        BigDecimal mensual,
        BigDecimal anual) {

}
