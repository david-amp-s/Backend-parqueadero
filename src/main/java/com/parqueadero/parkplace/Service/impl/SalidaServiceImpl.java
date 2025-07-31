package com.parqueadero.parkplace.Service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.parqueadero.parkplace.Service.SalidaService;
import com.parqueadero.parkplace.dto.SalidaCreateDto;
import com.parqueadero.parkplace.dto.SalidaDto;
import com.parqueadero.parkplace.enums.TipoVehiculo;
import com.parqueadero.parkplace.exception.IngresoNoEncontrado;
import com.parqueadero.parkplace.model.Ingreso;
import com.parqueadero.parkplace.model.Tarifa;
import com.parqueadero.parkplace.repository.IngresoRepository;
import com.parqueadero.parkplace.repository.SalidaRepository;
import com.parqueadero.parkplace.repository.TarifaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalidaServiceImpl implements SalidaService {
    private final SalidaRepository salidaRepository;

    private final IngresoRepository ingresoRepository;

    private final TarifaRepository tarifaRepository;

    @Override
    public SalidaDto registrarSalida(SalidaCreateDto salida) {

        Ingreso ingreso = ingresoRepository.findById(salida.ingreso_id()).orElseThrow(() -> new IngresoNoEncontrado());

        LocalDateTime fechaSalida = LocalDateTime.now();
        Duration duracion = Duration.between(ingreso.getFechaIngreso(), fechaSalida);
        Long minutos = duracion.toMinutes();

        TipoVehiculo tipoVehiculo = ingreso.getVehiculo().getTipoVehiculo();
        Tarifa tarifa = tarifaRepository.findByTipoVehiculo(tipoVehiculo);
        int valorTotal;
        if (tarifa.isTarifaFija()) {
            valorTotal = tarifa.getValorTarifaFija();
        } else {
            valorTotal = tarifa.getValorMinuto() * minutos.intValue();
        }
    }

    @Override
    public SalidaDto obtenerSalidaPorId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerSalidaPorId'");
    }

    @Override
    public List<SalidaDto> listarTodasSalidas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarTodasSalidas'");
    }

}
