package com.parqueadero.parkplace.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.parqueadero.parkplace.Service.VehiculoService;
import com.parqueadero.parkplace.dto.ClienteResponsiveDto;
import com.parqueadero.parkplace.dto.VehiculoCreateDto;
import com.parqueadero.parkplace.dto.VehiculoDto;
import com.parqueadero.parkplace.exception.ClienteNoEncontradoException;
import com.parqueadero.parkplace.exception.TipoVehiculoException;
import com.parqueadero.parkplace.exception.VehiculoNoEncontrado;
import com.parqueadero.parkplace.model.Cliente;
import com.parqueadero.parkplace.model.TipoVehiculoEnt;
import com.parqueadero.parkplace.model.Vehiculo;
import com.parqueadero.parkplace.repository.ClienteRepository;
import com.parqueadero.parkplace.repository.TipoVehiculoEntRepository;
import com.parqueadero.parkplace.repository.VehiculoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehiculoServiceImpl implements VehiculoService {
        private final VehiculoRepository vehiculoRepository;
        private final ClienteRepository clienteRepository;
        private final TipoVehiculoEntRepository tipoVehiculoEntRepository;

        private ClienteResponsiveDto convertorClienteResponsive(Cliente cliente) {
                return new ClienteResponsiveDto(cliente.getId(), cliente.getNombre(), cliente.getCorreo());
        }

        private VehiculoDto convertirVehiculoDto(Vehiculo vehiculo) {
                return new VehiculoDto(
                                vehiculo.getId(),
                                vehiculo.getPlaca(),
                                vehiculo.getTipoVehiculoEnt(),
                                convertorClienteResponsive(vehiculo.getCliente()));
        }

        @Override
        public VehiculoDto crear(VehiculoCreateDto dto) {
                TipoVehiculoEnt tipoVehiculo = tipoVehiculoEntRepository.findByTipo(dto.tipoVehiculo())
                                .orElseThrow(() -> new TipoVehiculoException());
                Cliente cliente = clienteRepository.findByCedula(dto.cedula())
                                .orElseThrow(() -> new ClienteNoEncontradoException(dto.cedula()));
                Vehiculo vehiculo = Vehiculo.builder()
                                .placa(dto.placa())
                                .tipoVehiculoEnt(tipoVehiculo)
                                .cliente(cliente)
                                .ingreso(false)
                                .salida(false)
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
        public VehiculoDto buscarPorId(Long id) {
                Vehiculo vehiculo = vehiculoRepository.findById(id)
                                .orElseThrow(() -> new VehiculoNoEncontrado());
                return new VehiculoDto(vehiculo.getId(), vehiculo.getPlaca(), vehiculo.getTipoVehiculoEnt(),
                                convertorClienteResponsive(vehiculo.getCliente()));
        }

        @Override
        public VehiculoDto buscarPorPlaca(String placa) {
                Vehiculo vehiculo = vehiculoRepository.findByPlaca(placa)
                                .orElseThrow(() -> new VehiculoNoEncontrado());
                return new VehiculoDto(vehiculo.getId(), vehiculo.getPlaca(), vehiculo.getTipoVehiculoEnt(),
                                convertorClienteResponsive(vehiculo.getCliente()));
        }

        @Override
        public VehiculoDto corregirVehiculo(String placa, VehiculoCreateDto dto) {
                TipoVehiculoEnt tipoVehiculo = tipoVehiculoEntRepository.findByTipo(dto.tipoVehiculo())
                                .orElseThrow(() -> new TipoVehiculoException());
                Vehiculo vehiculo = vehiculoRepository.findByPlaca(placa)
                                .orElseThrow(() -> new VehiculoNoEncontrado());
                Cliente cliente = clienteRepository.findByCedula(dto.cedula())
                                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
                vehiculo.setPlaca(dto.placa());
                vehiculo.setTipoVehiculoEnt(tipoVehiculo);
                vehiculo.setCliente(cliente);
                vehiculoRepository.save(vehiculo);
                return convertirVehiculoDto(vehiculo);
        }

        @Override
        public void eliminarVehiculo(String placa) {
                if (!vehiculoRepository.existsByPlaca(placa)) {
                        throw new VehiculoNoEncontrado();
                }
                vehiculoRepository.deleteByPlaca(placa);
        }
}
