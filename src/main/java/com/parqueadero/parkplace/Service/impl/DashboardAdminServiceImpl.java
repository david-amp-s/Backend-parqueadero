package com.parqueadero.parkplace.Service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.parqueadero.parkplace.Service.DashboardAdminService;
import com.parqueadero.parkplace.dto.DashboardAdminDto;
import com.parqueadero.parkplace.dto.IngresoDto;
import com.parqueadero.parkplace.dto.UsuarioDto;
import com.parqueadero.parkplace.dto.VehiculoDto;
import com.parqueadero.parkplace.enums.EstadoEspacio;
import com.parqueadero.parkplace.repository.ClienteRepository;
import com.parqueadero.parkplace.repository.EspacioRepository;
import com.parqueadero.parkplace.repository.FacturaRepository;
import com.parqueadero.parkplace.repository.IngresoRepository;
import com.parqueadero.parkplace.repository.UsuarioRepository;
import com.parqueadero.parkplace.repository.VehiculoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardAdminServiceImpl implements DashboardAdminService {
        private final EspacioRepository espacioRepository;
        private final FacturaRepository facturaRepository;
        private final VehiculoRepository vehiculoRepository;
        private final ClienteRepository clienteRepository;
        private final UsuarioRepository usuarioRepository;
        private final IngresoRepository ingresoRepository;

        @Override
        public DashboardAdminDto obtenerDatosDashboard() {
                Integer totalVehiculos = espacioRepository.countByTipoEspacio(EstadoEspacio.OCUPADO);

                LocalDate hoy = LocalDate.now();
                LocalDateTime inicio = hoy.atStartOfDay();
                LocalDateTime fin = hoy.atTime(23, 59, 59);

                Long vehiculosDentro = vehiculoRepository.countByIngresoTrueAndSalidaFalse();

                Integer totalSalidas = facturaRepository.countByFechaBetween(inicio, fin);

                Integer espaciosDisponibles = espacioRepository.countByTipoEspacio(EstadoEspacio.DISPONIBLE);

                Double tazaDeOcupacion = espacioRepository.tasaOcupacion();

                Long totalClientes = clienteRepository.count();
                BigDecimal diario = facturaRepository.obtenerTotalDiario();
                BigDecimal mensual = facturaRepository.obtenerTotalMensual();
                BigDecimal semana = facturaRepository.obtenerTotalSemanal();
                List<UsuarioDto> listadoUsuarios = usuarioRepository.findAll().stream()
                                .map(u -> new UsuarioDto(u.getId(), u.getNombre(), u.getEmail(),
                                                u.getRol().getNombre()))
                                .toList();
                List<IngresoDto> listadoVehiculosRecientes = ingresoRepository.findTop10ByOrderByFechaIngresoDesc()
                                .stream()
                                .map(i -> new IngresoDto(i.getId(), i.getVehiculo().getPlaca(),
                                                i.getVehiculo().getTipoVehiculoEnt().getTipo(),
                                                i.getEspacio().getCodigo(),
                                                i.getFechaIngreso()))
                                .toList();

                return new DashboardAdminDto(vehiculosDentro, totalSalidas, espaciosDisponibles, tazaDeOcupacion,
                                totalClientes,
                                diario, mensual, semana, listadoUsuarios, listadoVehiculosRecientes);
        }

}
