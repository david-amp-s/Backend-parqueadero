package com.parqueadero.parkplace.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.parqueadero.parkplace.Service.VehiculoService;
import com.parqueadero.parkplace.dto.ClienteResponsiveDto;
import com.parqueadero.parkplace.dto.VehiculoCreateDto;
import com.parqueadero.parkplace.dto.VehiculoDto;
import com.parqueadero.parkplace.exception.VehiculoNoEncontrado;
import com.parqueadero.parkplace.model.Cliente;
import com.parqueadero.parkplace.model.Vehiculo;
import com.parqueadero.parkplace.repository.VehiculoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehiculoServiceImpl implements VehiculoService {
        private final VehiculoRepository vehiculoRepository;

        private ClienteResponsiveDto convertorClienteResponsive(Cliente cliente) {
                return new ClienteResponsiveDto(cliente.getId(), cliente.getNombre(), cliente.getCorreo());
        }

        private VehiculoDto convertirVehiculoDto(Vehiculo vehiculo) {
                return new VehiculoDto(
                                vehiculo.getId(),
                                vehiculo.getPlaca(),
                                vehiculo.getTipoVehiculo(),
                                convertorClienteResponsive(vehiculo.getCliente()));
        }

        @Override
        public VehiculoDto crear(VehiculoCreateDto dto) {
                if (dto.cliente() == null) {
                        throw new IllegalArgumentException("El cliente es obligatorio");
                }
                Vehiculo vehiculo = Vehiculo.builder()
                                .placa(dto.placa())
                                .tipoVehiculo(dto.tipoVehiculo())
                                .cliente(dto.cliente())
                                .build();
                vehiculoRepository.save(vehiculo);
                return convertirVehiculoDto(vehiculo);
        }

        @Override
        public List<VehiculoDto> listar() {
                return vehiculoRepository.findAll().stream()
                                .map(v -> convertirVehiculoDto(v))
                                .toList();

        }

        @Override
        public VehiculoDto buscarPorPlaca(String placa) {
                Vehiculo vehiculo = vehiculoRepository.findByPlaca(placa)
                                .orElseThrow(() -> new VehiculoNoEncontrado(placa));
                return new VehiculoDto(vehiculo.getId(), vehiculo.getPlaca(), vehiculo.getTipoVehiculo(),
                                convertorClienteResponsive(vehiculo.getCliente()));
        }

        @Override
        public VehiculoDto corregirVehiculo(String placa, VehiculoCreateDto dto) {
                Vehiculo vehiculo = vehiculoRepository.findByPlaca(placa)
                                .orElseThrow(() -> new VehiculoNoEncontrado(placa));
                vehiculo.setPlaca(dto.placa());
                vehiculo.setTipoVehiculo(dto.tipoVehiculo());
                vehiculo.setCliente(dto.cliente());
                vehiculoRepository.save(vehiculo);
                return convertirVehiculoDto(vehiculo);
        }

        @Override
        public void eliminarVehiculo(String placa) {
                if (!vehiculoRepository.existsByPlaca(placa)) {
                        throw new VehiculoNoEncontrado(placa);
                }
                vehiculoRepository.deleteByPlaca(placa);
        }
}
