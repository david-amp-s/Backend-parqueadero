package com.parqueadero.parkplace.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.parqueadero.parkplace.Service.TipoVehiculoService;
import com.parqueadero.parkplace.dto.TipoVehiculoCreateDto;
import com.parqueadero.parkplace.dto.TipoVehiculoDto;
import com.parqueadero.parkplace.model.TipoVehiculoEnt;
import com.parqueadero.parkplace.repository.TipoVehiculoEntRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TipoVehiculoServiceImpl implements TipoVehiculoService {
    private final TipoVehiculoEntRepository tipoVehiculoRepository;

    @Override
    public TipoVehiculoDto crearTipoVehiculo(TipoVehiculoCreateDto dto) {
        tipoVehiculoRepository.findByTipo(dto.tipo()).ifPresent(tipovehiculo -> {
            throw new IllegalArgumentException("El tipo de vehiculo ya existe");
        });
        TipoVehiculoEnt tipoVehiculoEnt = TipoVehiculoEnt.builder()
                .tipo(dto.tipo())
                .build();
        tipoVehiculoRepository.save(tipoVehiculoEnt);
        return new TipoVehiculoDto(
                tipoVehiculoEnt.getId(),
                tipoVehiculoEnt.getTipo());
    }

    @Override
    public TipoVehiculoDto obtenerTipoVehiculoPorId(Long id) {
        TipoVehiculoEnt tipoVehiculoEnt = tipoVehiculoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("El tipo de vehiculo no existe"));
        return new TipoVehiculoDto(
                tipoVehiculoEnt.getId(),
                tipoVehiculoEnt.getTipo());
    }

    @Override
    public List<TipoVehiculoDto> obtenerTodosLosTiposVehiculos() {
        List<TipoVehiculoEnt> tiposVehiculosEnt = tipoVehiculoRepository.findAll();
        return tiposVehiculosEnt.stream()
                .map(tipoVehiculoEnt -> new TipoVehiculoDto(
                        tipoVehiculoEnt.getId(),
                        tipoVehiculoEnt.getTipo()))
                .toList();
    }

    @Override
    public void eliminarTipoVehiculo(Long id) {
        TipoVehiculoEnt tipoVehiculoEnt = tipoVehiculoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("El tipo de vehiculo no existe"));
        tipoVehiculoRepository.delete(tipoVehiculoEnt);
    }

}
