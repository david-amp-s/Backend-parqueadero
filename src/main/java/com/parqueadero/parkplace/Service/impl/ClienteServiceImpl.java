package com.parqueadero.parkplace.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.parqueadero.parkplace.Service.ClienteService;
import com.parqueadero.parkplace.dto.ClienteCreateDto;
import com.parqueadero.parkplace.dto.ClienteDto;
import com.parqueadero.parkplace.dto.InfoClienteDto;
import com.parqueadero.parkplace.exception.ClienteNoEncontradoException;
import com.parqueadero.parkplace.exception.TipoClienteNoEncontradoException;
import com.parqueadero.parkplace.model.Cliente;
import com.parqueadero.parkplace.model.TipoCliente;
import com.parqueadero.parkplace.repository.ClienteRepository;
import com.parqueadero.parkplace.repository.TipoClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {
        private final ClienteRepository clienteRepository;
        private final TipoClienteRepository tipoClienteRepository;

        private TipoCliente buscarTipoCliente(String tipoCliente) {
                return tipoClienteRepository.findByNombre(tipoCliente)
                                .orElseThrow(() -> new TipoClienteNoEncontradoException());
        }

        @Override
        public ClienteDto crear(ClienteCreateDto dto) {
                TipoCliente tipoCliente;
                if (dto.tipoCliente().isBlank()) {
                        tipoCliente = buscarTipoCliente("CLIENTE");
                } else {
                        tipoCliente = buscarTipoCliente(dto.tipoCliente());

                }
                Cliente cliente = Cliente.builder()
                                .nombre(dto.nombre())
                                .correo(dto.correo())
                                .cedula(dto.cedula())
                                .tipoCliente(tipoCliente)
                                .build();
                clienteRepository.save(cliente);
                return new ClienteDto(cliente.getId(), cliente.getNombre(), cliente.getCorreo(), cliente.getCedula(),
                                cliente.getTipoCliente().getNombre());
        }

        @Override
        public List<ClienteDto> listar() {
                return clienteRepository.findAll().stream()
                                .map(c -> new ClienteDto(c.getId(), c.getNombre(), c.getCorreo(), c.getCedula(),
                                                c.getTipoCliente().getNombre()))
                                .toList();
        }

        @Override
        public ClienteDto buscar(String cedula) {
                Cliente cliente = clienteRepository.findByCedula(cedula)
                                .orElseThrow(() -> new ClienteNoEncontradoException(cedula));
                return new ClienteDto(cliente.getId(), cliente.getNombre(), cliente.getCorreo(), cliente.getCedula(),
                                cliente.getTipoCliente().getNombre());
        }

        @Override
        public ClienteDto actualizar(String cedula, ClienteCreateDto dto) {
                TipoCliente tipoCliente = buscarTipoCliente(dto.tipoCliente());
                Cliente cliente = clienteRepository.findByCedula(cedula)
                                .orElseThrow(() -> new ClienteNoEncontradoException(cedula));
                cliente.setNombre(dto.nombre());
                cliente.setCedula(dto.cedula());
                cliente.setCorreo(dto.correo());
                cliente.setTipoCliente(tipoCliente);
                clienteRepository.save(cliente);
                return new ClienteDto(cliente.getId(), cliente.getNombre(), cliente.getCorreo(), cliente.getCedula(),
                                cliente.getTipoCliente().getNombre());
        }

        @Override
        public void eliminar(String cedula) {
                if (!clienteRepository.existsByCedula(cedula)) {
                        throw new RuntimeException("El cliente no existe");
                }
                clienteRepository.deleteByCedula(cedula);
        }

        @Override
        public InfoClienteDto infoClientes() {
                Integer estudiantes = clienteRepository.countByTipo("ESTUDIANTE");
                Integer clientes = clienteRepository.countByTipo("CLIENTE");
                Integer totalClientes = clienteRepository.totalClientes();
                return new InfoClienteDto(totalClientes, estudiantes, clientes);
        }
}
