package com.parqueadero.parkplace.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.parqueadero.parkplace.Service.EspacioService;
import com.parqueadero.parkplace.dto.EspacioCreateDto;
import com.parqueadero.parkplace.dto.EspacioDto;
import com.parqueadero.parkplace.enums.EstadoEspacio;
import com.parqueadero.parkplace.enums.TipoVehiculo;
import com.parqueadero.parkplace.model.Espacio;
import com.parqueadero.parkplace.repository.EspacioRepository;

import lombok.NoArgsConstructor;
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
        return new EspacioDto(espacio.getId(), espacio.getCodigo(), espacio.getTipoEspacio(),
                espacio.getTipoVehiculoPermitido());
    }

    @Override
    public List<Espacio> espaciosDisponiblesCarro() {
        return espacioRepository.findByTipoEspacioAndTipoVehiculoPermitidoByIdAsc(EstadoEspacio.DISPONIBLE,
                TipoVehiculo.CARRO);
    }

    @Override
    public List<Espacio> espaciosDisponiblesMoto() {
        return espacioRepository.findByTipoEspacioAndTipoVehiculoPermitidoByIdAsc(EstadoEspacio.DISPONIBLE,
                TipoVehiculo.MOTO);
    }

    @Override
    public List<Espacio> espaciosDisponiblesBicicleta() {
        return espacioRepository.findByTipoEspacioAndTipoVehiculoPermitidoByIdAsc(EstadoEspacio.DISPONIBLE,
                TipoVehiculo.BICICLETA);
    }

    @Override
    public EstadoEspacio asignarEspacio(Espacio espacio, TipoVehiculo tipoVehiculo) {
        if () {

        }
    }

    @Override
    public EstadoEspacio liberarEspacio() {
    }

}
