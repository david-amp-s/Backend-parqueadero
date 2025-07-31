package com.parqueadero.parkplace.Service.impl;

import org.springframework.stereotype.Service;

import com.parqueadero.parkplace.Service.TarifaService;
import com.parqueadero.parkplace.dto.TarifaCreateDto;
import com.parqueadero.parkplace.dto.TarifaDto;
import com.parqueadero.parkplace.model.Tarifa;
import com.parqueadero.parkplace.repository.TarifaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TarifaServiceImpl implements TarifaService {
    private final TarifaRepository tarifaRepository;

    @Override
    public TarifaDto cambiarTarifaMinuto(TarifaCreateDto dto) {
        Tarifa tarifa = tarifaRepository.findByTipoVehiculo(dto.tipoVehiculo());
        tarifa.setValorMinuto(dto.nuevaTarifa());
        tarifaRepository.save(tarifa);
        return new TarifaDto(tarifa.getId(), tarifa.getTipoVehiculo(), tarifa.getValorMinuto());
    }

    @Override
    public TarifaDto cambiarTarifaHora(TarifaCreateDto dto) {
        Tarifa tarifa = tarifaRepository.findByTipoVehiculo(dto.tipoVehiculo());
        tarifa.setValorHora(dto.nuevaTarifa());
        tarifaRepository.save(tarifa);
        return new TarifaDto(tarifa.getId(), tarifa.getTipoVehiculo(), tarifa.getValorHora());
    }

    @Override
    public TarifaDto cambiarTarifaFija(TarifaCreateDto dto) {
        Tarifa tarifa = tarifaRepository.findByTipoVehiculo(dto.tipoVehiculo());
        tarifa.setValorTarifaFija(dto.nuevaTarifa());
        tarifaRepository.save(tarifa);
        return new TarifaDto(tarifa.getId(), tarifa.getTipoVehiculo(), tarifa.getValorTarifaFija());
    }

}
