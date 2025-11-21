package com.parqueadero.parkplace.Service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.parqueadero.parkplace.Service.SalidaService;
import com.parqueadero.parkplace.dto.SalidaCreateDto;
import com.parqueadero.parkplace.dto.SalidaDto;
import com.parqueadero.parkplace.enums.EstadoEspacio;
import com.parqueadero.parkplace.exception.IngresoNoEncontrado;
import com.parqueadero.parkplace.exception.SalidaNoEncontrada;
import com.parqueadero.parkplace.exception.TarifaNoEncontradException;
import com.parqueadero.parkplace.exception.TipoVehiculoNoRegistrado;
import com.parqueadero.parkplace.exception.VehiculoNoEncontrado;
import com.parqueadero.parkplace.model.Cliente;
import com.parqueadero.parkplace.model.Espacio;
import com.parqueadero.parkplace.model.Ingreso;
import com.parqueadero.parkplace.model.Salida;
import com.parqueadero.parkplace.model.Tarifa;
import com.parqueadero.parkplace.model.TipoVehiculoEnt;
import com.parqueadero.parkplace.model.Vehiculo;
import com.parqueadero.parkplace.repository.EspacioRepository;
import com.parqueadero.parkplace.repository.IngresoRepository;
import com.parqueadero.parkplace.repository.SalidaRepository;
import com.parqueadero.parkplace.repository.TarifaRepository;
import com.parqueadero.parkplace.repository.VehiculoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalidaServiceImpl implements SalidaService {
    private final SalidaRepository salidaRepository;

    private final IngresoRepository ingresoRepository;

    private final TarifaRepository tarifaRepository;

    private final EspacioRepository espacioRepository;

    private final VehiculoRepository vehiculoRepository;

    @Override
    public SalidaDto registrarSalida(SalidaCreateDto salida) {
        Vehiculo vehiculo = vehiculoRepository.findByPlaca(salida.placa())
                .orElseThrow(() -> new VehiculoNoEncontrado());

        if (vehiculo.getIngreso() == false) {
            throw new RuntimeException("Vehiculo no ingresado");
        }

        Ingreso ingreso = ingresoRepository.findFirstByVehiculoOrderByFechaIngresoDesc(vehiculo)
                .orElseThrow(() -> new IngresoNoEncontrado());

        LocalDateTime fechaSalida = LocalDateTime.now();
        Duration duracion = Duration.between(ingreso.getFechaIngreso(), fechaSalida);
        Long minutos = duracion.toMinutes();

        TipoVehiculoEnt tipoVehiculo = ingreso.getVehiculo().getTipoVehiculoEnt();
        Tarifa tarifa = tarifaRepository
                .findByTipoVehiculoEntAndTipoCliente(tipoVehiculo, vehiculo.getCliente().getTipoCliente())
                .orElseThrow(() -> new TarifaNoEncontradException());
        int valorTotal;
        Integer valorPorMinutos = tarifa.getValorMinuto() * minutos.intValue();
        Integer valorTarifaFija = tarifa.getValorTarifaFija();
        if (valorTarifaFija == 0) {
            valorTotal = valorPorMinutos;
        } else {
            valorTotal = Math.min(valorPorMinutos, valorTarifaFija);
        }

        Salida nuevaSalida = Salida.builder()
                .ingreso(ingreso)
                .fechaSalida(fechaSalida)
                .total(valorTotal)
                .build();
        salidaRepository.save(nuevaSalida);

        Espacio espacio = ingreso.getEspacio();
        espacio.setTipoEspacio(EstadoEspacio.DISPONIBLE);
        espacioRepository.save(espacio);
        vehiculo.setIngreso(false);
        vehiculo.setSalida(true);
        vehiculoRepository.save(vehiculo);
        return new SalidaDto(nuevaSalida.getId(), nuevaSalida.getIngreso().getFechaIngreso(), fechaSalida,
                nuevaSalida.getTotal());

    }

    @Override
    public SalidaDto obtenerSalidaPorId(Long id) {
        Salida salida = salidaRepository.findById(id).orElseThrow(() -> new SalidaNoEncontrada());
        return new SalidaDto(salida.getId(), salida.getIngreso().getFechaIngreso(), salida.getFechaSalida(),
                salida.getTotal());
    }

    @Override
    public List<SalidaDto> listarTodasSalidas() {
        return salidaRepository.findAll().stream()
                .map(s -> new SalidaDto(s.getId(), s.getIngreso().getFechaIngreso(), s.getFechaSalida(), s.getTotal()))
                .toList();
    }

    @Override
    @Transactional
    public void cancelarSalida(String placa) {
        Vehiculo vehiculo = vehiculoRepository.findByPlaca(placa).orElseThrow(() -> new VehiculoNoEncontrado());
        Ingreso ingreso = ingresoRepository.findFirstByVehiculoOrderByFechaIngresoDesc(vehiculo)
                .orElseThrow(() -> new IngresoNoEncontrado());
        vehiculo.setIngreso(true);
        vehiculo.setSalida(false);
        vehiculoRepository.save(vehiculo);
        Espacio espacio = ingreso.getEspacio();
        espacio.setTipoEspacio(EstadoEspacio.OCUPADO);
        espacioRepository.save(espacio);
        salidaRepository.deleteByIngreso(ingreso);
    }

}
