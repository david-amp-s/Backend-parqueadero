package com.parqueadero.parkplace.Service.impl;

import org.springframework.stereotype.Service;

import com.parqueadero.parkplace.Service.TarifaService;
import com.parqueadero.parkplace.dto.TarifaCreateDto;
import com.parqueadero.parkplace.dto.TarifaDto;
import com.parqueadero.parkplace.exception.TipoVehiculoException;
import com.parqueadero.parkplace.exception.TipoVehiculoNoRegistrado;
import com.parqueadero.parkplace.model.Tarifa;
import com.parqueadero.parkplace.model.TipoVehiculoEnt;
import com.parqueadero.parkplace.repository.TarifaRepository;
import com.parqueadero.parkplace.repository.TipoVehiculoEntRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TarifaServiceImpl implements TarifaService {
    private final TarifaRepository tarifaRepository;
    private final TipoVehiculoEntRepository tipoVehiculoEntRepository;

    @Override
    public TarifaDto cambiarTarifaMinuto(TarifaCreateDto dto) {
        TipoVehiculoEnt tipoVehiculo = tipoVehiculoEntRepository.findByTipo(dto.vehiculo())
                .orElseThrow(() -> new TipoVehiculoException());
        Tarifa tarifa = tarifaRepository.findByTipoVehiculoEnt(tipoVehiculo)
                .orElseThrow(() -> new TipoVehiculoNoRegistrado());
        tarifa.setValorMinuto(dto.nuevaTarifa());
        tarifaRepository.save(tarifa);
        return new TarifaDto(tarifa.getId(), tarifa.getTipoVehiculoEnt(), tarifa.getValorMinuto());
    }

    @Override
    public TarifaDto cambiarTarifaHora(TarifaCreateDto dto) {
        TipoVehiculoEnt tipoVehiculo = tipoVehiculoEntRepository.findByTipo(dto.vehiculo())
                .orElseThrow(() -> new TipoVehiculoException());
        Tarifa tarifa = tarifaRepository.findByTipoVehiculoEnt(tipoVehiculo)
                .orElseThrow(() -> new TipoVehiculoNoRegistrado());
        tarifa.setValorHora(dto.nuevaTarifa());
        tarifaRepository.save(tarifa);
        return new TarifaDto(tarifa.getId(), tarifa.getTipoVehiculoEnt(), tarifa.getValorHora());
    }

    @Override
    public TarifaDto cambiarTarifaFija(TarifaCreateDto dto) {
        TipoVehiculoEnt tipoVehiculo = tipoVehiculoEntRepository.findByTipo(dto.vehiculo())
                .orElseThrow(() -> new TipoVehiculoException());
        Tarifa tarifa = tarifaRepository.findByTipoVehiculoEnt(tipoVehiculo)
                .orElseThrow(() -> new TipoVehiculoNoRegistrado());
        tarifa.setValorTarifaFija(dto.nuevaTarifa());
        tarifaRepository.save(tarifa);
        return new TarifaDto(tarifa.getId(), tarifa.getTipoVehiculoEnt(), tarifa.getValorTarifaFija());
    }

}
