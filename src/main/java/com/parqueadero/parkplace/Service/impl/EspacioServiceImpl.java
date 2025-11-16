package com.parqueadero.parkplace.Service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.parqueadero.parkplace.Service.EspacioService;
import com.parqueadero.parkplace.dto.EspacioCreateDto;
import com.parqueadero.parkplace.dto.EspacioDto;
import com.parqueadero.parkplace.enums.EstadoEspacio;
import com.parqueadero.parkplace.exception.TipoVehiculoException;
import com.parqueadero.parkplace.model.Espacio;
import com.parqueadero.parkplace.model.TipoVehiculoEnt;
import com.parqueadero.parkplace.repository.EspacioRepository;
import com.parqueadero.parkplace.repository.TipoVehiculoEntRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EspacioServiceImpl implements EspacioService {
    private final EspacioRepository espacioRepository;
    private final TipoVehiculoEntRepository tipoVehiculoEntRepository;

    @Override
    public EspacioDto crearEspacio(EspacioCreateDto dto) {
        TipoVehiculoEnt tipoVehiculoEnt = tipoVehiculoEntRepository.findById(dto.tipoVehiculoPermitido())
                .orElseThrow(() -> new TipoVehiculoException());
        Espacio espacio = Espacio.builder()
                .codigo(dto.codigo())
                .tipoEspacio(dto.tipoEspacio())
                .tipoVehiculoEnt(tipoVehiculoEnt)
                .build();
        espacioRepository.save(espacio);
        return new EspacioDto(espacio.getId(), espacio.getCodigo(), espacio.getTipoEspacio(),
                espacio.getTipoVehiculoEnt());
    }

    @Override
    public List<Espacio> espaciosDisponibles(String vehiculo) {
        TipoVehiculoEnt tipoVehiculoEnt = tipoVehiculoEntRepository.findByTipo(vehiculo.toUpperCase())
                .orElseThrow(() -> new TipoVehiculoException());
        return espacioRepository.findByTipoEspacioAndTipoVehiculoEntOrderByIdAsc(EstadoEspacio.DISPONIBLE,
                tipoVehiculoEnt);
    }

    @Override
    public EspacioDto asignarEspacio(String vehiculo) {
        TipoVehiculoEnt tipoVehiculoEnt = tipoVehiculoEntRepository.findByTipo(vehiculo.toUpperCase())
                .orElseThrow(() -> new TipoVehiculoException());
        Optional<Espacio> espacioDisponible = espacioRepository
                .findFirstByTipoEspacioAndTipoVehiculoEntOrderByIdAsc(EstadoEspacio.DISPONIBLE, tipoVehiculoEnt);
        if (espacioDisponible.isPresent()) {
            Espacio espacioAsignado = espacioDisponible.get();
            espacioAsignado.setTipoEspacio(EstadoEspacio.OCUPADO);
            espacioRepository.save(espacioAsignado);
            return new EspacioDto(espacioAsignado.getId(), espacioAsignado.getCodigo(),
                    espacioAsignado.getTipoEspacio(), espacioAsignado.getTipoVehiculoEnt());
        } else {
            throw new RuntimeException("No hay espacios disponibles");
        }
    }

    @Override
    public EspacioDto liberarEspacio(String codigo) {
        Optional<Espacio> espacioCodigo = espacioRepository.findByCodigo(codigo);
        Espacio espacio = espacioCodigo.get();
        if (espacio.getTipoEspacio() == EstadoEspacio.OCUPADO) {
            espacio.setTipoEspacio(EstadoEspacio.DISPONIBLE);
            espacioRepository.save(espacio);
            return new EspacioDto(espacio.getId(), espacio.getCodigo(), espacio.getTipoEspacio(),
                    espacio.getTipoVehiculoEnt());
        } else {
            throw new RuntimeException("El espacio no se encuentra ocupado");
        }
    }

}
