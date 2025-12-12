package com.parqueadero.parkplace.Service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.stereotype.Service;

import com.parqueadero.parkplace.Service.IngresoService;
import com.parqueadero.parkplace.dto.IngresoCreateDto;
import com.parqueadero.parkplace.dto.IngresoDto;
import com.parqueadero.parkplace.enums.EstadoEspacio;
import com.parqueadero.parkplace.exception.EspaciosDisponiblesNoEncontradosException;
import com.parqueadero.parkplace.exception.IngresoNoEncontrado;
import com.parqueadero.parkplace.exception.VehiculoConSalidaPendienteException;
import com.parqueadero.parkplace.exception.VehiculoIngresadoException;
import com.parqueadero.parkplace.exception.VehiculoNoEncontrado;
import com.parqueadero.parkplace.model.Espacio;
import com.parqueadero.parkplace.model.Ingreso;
import com.parqueadero.parkplace.model.Vehiculo;
import com.parqueadero.parkplace.repository.EspacioRepository;
import com.parqueadero.parkplace.repository.IngresoRepository;
import com.parqueadero.parkplace.repository.VehiculoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngresoServiceImpl implements IngresoService {

    private final IngresoRepository ingresoRepository;

    private final VehiculoRepository vehiculoRepository;

    private final EspacioRepository espacioRepository;

    private IngresoDto conversorDto(Ingreso ingreso) {
        return new IngresoDto(ingreso.getId(), ingreso.getVehiculo().getPlaca(),
                ingreso.getVehiculo().getTipoVehiculoEnt().getTipo(), ingreso.getEspacio().getCodigo(),
                ingreso.getFechaIngreso());
    }

    @Override
    public IngresoDto registrarIngreso(IngresoCreateDto dto) {
        Vehiculo vehiculo = vehiculoRepository.findByPlaca(dto.placa())
                .orElseThrow(() -> new VehiculoNoEncontrado());
        Espacio espacio = espacioRepository.findFirstByTipoEspacioAndTipoVehiculoEntOrderByIdAsc(
                EstadoEspacio.DISPONIBLE, vehiculo.getTipoVehiculoEnt())
                .orElseThrow(() -> new EspaciosDisponiblesNoEncontradosException());

        if (vehiculo.getIngreso()) {
            throw new VehiculoIngresadoException();
        }

        if (vehiculo.getSalida()) {
            throw new VehiculoConSalidaPendienteException();
        }
        Ingreso ingreso = Ingreso.builder()
                .vehiculo(vehiculo)
                .espacio(espacio)
                .fechaIngreso(LocalDateTime.now(ZoneId.of("America/Bogota")))
                .build();
        ingresoRepository.save(ingreso);
        espacio.setTipoEspacio(EstadoEspacio.OCUPADO);
        espacioRepository.save(espacio);
        vehiculo.setIngreso(true);
        vehiculoRepository.save(vehiculo);
        return conversorDto(ingreso);
    }

    @Override
    public IngresoDto obteneIngresoPorId(Long id) {
        Ingreso ingreso = ingresoRepository.findById(id).orElseThrow(() -> new IngresoNoEncontrado());
        return conversorDto(ingreso);
    }

    @Override
    public List<IngresoDto> ListarTodosLosIngresos() {
        return ingresoRepository.findAll().stream()
                .map(i -> conversorDto(i)).toList();
    }

    @Override
    public void eliminarIngreso(Long id) {
        Ingreso ingreso = ingresoRepository.findById(id).orElseThrow(() -> new IngresoNoEncontrado());
        ingresoRepository.delete(ingreso);
    }

    @Override
    public List<IngresoDto> buscarPorFecha(LocalDateTime fecha) {
        LocalDateTime inicio = fecha.toLocalDate().atStartOfDay();
        LocalDateTime fin = inicio.plusDays(1);

        return ingresoRepository.findByFechaIngresoBetween(inicio, fin).stream()
                .map(this::conversorDto)
                .toList();
    }

    @Override
    public List<IngresoDto> buscarPorPlaca(String placa) {
        return ingresoRepository.findByVehiculo_Placa(placa).stream().map(p -> conversorDto(p)).toList();
    }

    @Override
    public List<IngresoDto> espaciosOcupados() {
        return ingresoRepository.findIngresosOcupados().stream().map(i -> conversorDto(i)).toList();
    }

}
