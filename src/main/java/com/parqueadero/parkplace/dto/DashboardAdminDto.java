package com.parqueadero.parkplace.dto;

import java.math.BigDecimal;
import java.util.List;

public record DashboardAdminDto(
                Long vehiculosDentro,
                Integer totalSalidas,
                Integer espaciosDisponibles,
                Double tazaDeOcupacion,
                Long totalClientes,
                BigDecimal diario,
                BigDecimal mensual,
                BigDecimal semana,
                List<UsuarioDto> listadoUsuarios,
                List<IngresoDto> listadoVehiculosRecientes) {
}
