package com.parqueadero.parkplace.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.parqueadero.parkplace.Service.TarifaService;
import com.parqueadero.parkplace.dto.TarifaCreateDto;
import com.parqueadero.parkplace.dto.TarifaDto;
import com.parqueadero.parkplace.exception.TarifaNoEncontradException;
import com.parqueadero.parkplace.exception.TipoClienteNoEncontradoException;
import com.parqueadero.parkplace.exception.TipoVehiculoException;
import com.parqueadero.parkplace.exception.TipoVehiculoNoRegistrado;
import com.parqueadero.parkplace.model.Tarifa;
import com.parqueadero.parkplace.model.TipoCliente;
import com.parqueadero.parkplace.model.TipoVehiculoEnt;
import com.parqueadero.parkplace.repository.TarifaRepository;
import com.parqueadero.parkplace.repository.TipoClienteRepository;
import com.parqueadero.parkplace.repository.TipoVehiculoEntRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TarifaServiceImpl implements TarifaService {
        private final TarifaRepository tarifaRepository;
        private final TipoVehiculoEntRepository tipoVehiculoEntRepository;
        private final TipoClienteRepository tipoClienteRepository;

        @Override
        public TarifaDto cambiarTarifaMinuto(TarifaCreateDto dto) {
                TipoVehiculoEnt tipoVehiculo = tipoVehiculoEntRepository.findByTipo(dto.vehiculo())
                                .orElseThrow(() -> new TipoVehiculoException());
                TipoCliente tipoCliente = tipoClienteRepository.findByNombre(dto.tipoCliente())
                                .orElseThrow(() -> new TipoClienteNoEncontradoException());
                Tarifa tarifa = tarifaRepository.findByTipoVehiculoEntAndTipoCliente(tipoVehiculo, tipoCliente)
                                .orElseThrow(() -> new TarifaNoEncontradException());
                tarifa.setValorMinuto(dto.valorMinuto());
                tarifaRepository.save(tarifa);
                return new TarifaDto(tarifa.getId(), tarifa.getTipoVehiculoEnt(), tarifa.getTipoCliente().getNombre(),
                                tarifa.getValorMinuto());
        }

        @Override
        public TarifaDto cambiarTarifaFija(TarifaCreateDto dto) {
                TipoVehiculoEnt tipoVehiculo = tipoVehiculoEntRepository.findByTipo(dto.vehiculo())
                                .orElseThrow(() -> new TipoVehiculoException());
                TipoCliente tipoCliente = tipoClienteRepository.findByNombre(dto.tipoCliente())
                                .orElseThrow(() -> new TipoClienteNoEncontradoException());
                Tarifa tarifa = tarifaRepository.findByTipoVehiculoEntAndTipoCliente(tipoVehiculo, tipoCliente)
                                .orElseThrow(() -> new TarifaNoEncontradException());
                tarifa.setValorTarifaFija(dto.valorTarifaFija());
                tarifaRepository.save(tarifa);
                return new TarifaDto(tarifa.getId(), tarifa.getTipoVehiculoEnt(), tarifa.getTipoCliente().getNombre(),
                                tarifa.getValorMinuto());
        }

        @Override
        public TarifaDto crearTarifa(TarifaCreateDto dto) {
                TipoVehiculoEnt tipoVehiculo = tipoVehiculoEntRepository.findByTipo(dto.vehiculo())
                                .orElseThrow(() -> new TipoVehiculoException());
                tarifaRepository.findByTipoVehiculoEnt(tipoVehiculo).ifPresent(t -> {
                        throw new RuntimeException("La tarifa para este tipo de vehículo ya existe");
                });

                TipoCliente tipoCliente = tipoClienteRepository.findByNombre(dto.tipoCliente())
                                .orElseThrow(() -> new TipoClienteNoEncontradoException());
                Tarifa tarifa = tarifaRepository.findByTipoVehiculoEntAndTipoCliente(tipoVehiculo, tipoCliente)
                                .orElseThrow(() -> new TarifaNoEncontradException());
                tarifa.setTipoVehiculoEnt(tipoVehiculo);
                tarifa.setValorMinuto(dto.valorMinuto());
                tarifa.setValorTarifaFija(dto.valorTarifaFija());
                tarifaRepository.save(tarifa);
                return new TarifaDto(tarifa.getId(), tarifa.getTipoVehiculoEnt(), tarifa.getTipoCliente().getNombre(),
                                tarifa.getValorMinuto());
        }

        @Override
        public List<TarifaDto> obtenerTodasLasTarifas() {
                List<Tarifa> tarifas = tarifaRepository.findAll();
                return tarifas.stream()
                                .map(tarifa -> new TarifaDto(tarifa.getId(), tarifa.getTipoVehiculoEnt(),
                                                tarifa.getTipoCliente().getNombre(),
                                                tarifa.getValorMinuto()))
                                .toList();
        }

}
