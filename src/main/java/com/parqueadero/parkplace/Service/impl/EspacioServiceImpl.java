package com.parqueadero.parkplace.Service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.parqueadero.parkplace.Service.EspacioService;
import com.parqueadero.parkplace.dto.EspacioCreateDto;
import com.parqueadero.parkplace.dto.EspacioDto;
import com.parqueadero.parkplace.enums.EstadoEspacio;
import com.parqueadero.parkplace.enums.TipoVehiculo;
import com.parqueadero.parkplace.model.Espacio;
import com.parqueadero.parkplace.repository.EspacioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EspacioServiceImpl implements EspacioService {
    private final EspacioRepository espacioRepository;

    @Override
    public EspacioDto crearEspacio(EspacioCreateDto dto) {
        Espacio espacio = Espacio.builder()
                .codigo(dto.codigo())
                .tipoEspacio(dto.tipoEspacio())
                .tipoVehiculoPermitido(dto.tipoVehiculoPermitido())
                .build();
        espacioRepository.save(espacio);
        return new EspacioDto(espacio.getId(), espacio.getCodigo(), espacio.getTipoEspacio(),
                espacio.getTipoVehiculoPermitido());
    }

    @Override
    public List<Espacio> espaciosDisponiblesCarro() {
        return espacioRepository.findByTipoEspacioAndTipoVehiculoPermitidoOrderByIdAsc(EstadoEspacio.DISPONIBLE,
                TipoVehiculo.CARRO);
    }

    @Override
    public List<Espacio> espaciosDisponiblesMoto() {
        return espacioRepository.findByTipoEspacioAndTipoVehiculoPermitidoOrderByIdAsc(EstadoEspacio.DISPONIBLE,
                TipoVehiculo.MOTO);
    }

    @Override
    public List<Espacio> espaciosDisponiblesBicicleta() {
        return espacioRepository.findByTipoEspacioAndTipoVehiculoPermitidoOrderByIdAsc(EstadoEspacio.DISPONIBLE,
                TipoVehiculo.BICICLETA);
    }

    @Override
    public EspacioDto asignarEspacio(TipoVehiculo tipoVehiculo) {
        Optional<Espacio> espacioDisponible = espacioRepository
                .findFirstByTipoEspacioAndTipoVehiculoPermitidoOrderByIdAsc(EstadoEspacio.DISPONIBLE, tipoVehiculo);
        if (espacioDisponible.isPresent()) {
            Espacio espacioAsignado = espacioDisponible.get();
            espacioAsignado.setTipoEspacio(EstadoEspacio.OCUPADO);
            espacioRepository.save(espacioAsignado);
            return new EspacioDto(espacioAsignado.getId(), espacioAsignado.getCodigo(),
                    espacioAsignado.getTipoEspacio(), espacioAsignado.getTipoVehiculoPermitido());
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
                    espacio.getTipoVehiculoPermitido());
        } else {
            throw new RuntimeException("El espacio no se encuentra ocupado");
        }
    }

}
