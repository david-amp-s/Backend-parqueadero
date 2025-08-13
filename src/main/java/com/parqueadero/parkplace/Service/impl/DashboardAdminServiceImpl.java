package com.parqueadero.parkplace.Service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.parqueadero.parkplace.Service.DashboardAdminService;
import com.parqueadero.parkplace.dto.DashboardAdminDto;
import com.parqueadero.parkplace.enums.EstadoEspacio;
import com.parqueadero.parkplace.repository.EspacioRepository;
import com.parqueadero.parkplace.repository.FacturaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardAdminServiceImpl implements DashboardAdminService {
    private final EspacioRepository espacioRepository;
    private final FacturaRepository facturaRepository;

    @Override
    public DashboardAdminDto obtenerDatosDashboard() {
        Integer totalVehiculos = espacioRepository.countByTipoEspacio(EstadoEspacio.OCUPADO);

        LocalDate hoy = LocalDate.now();
        LocalDateTime inicio = hoy.atStartOfDay();
        LocalDateTime fin = hoy.atTime(23, 59, 59);

        Integer totalSalidas = facturaRepository.countByFechaBetween(inicio, fin);
        Integer espaciosDisponibles = espacioRepository.countByTipoEspacio(EstadoEspacio.DISPONIBLE);

        BigDecimal diario = facturaRepository.obtenerTotalDiario();
        BigDecimal mensual = facturaRepository.obtenerTotalMensual();
        BigDecimal anual = facturaRepository.obtenerTotalAnual();

        return new DashboardAdminDto(totalVehiculos, totalSalidas, espaciosDisponibles, diario, mensual, anual);
    }

}
