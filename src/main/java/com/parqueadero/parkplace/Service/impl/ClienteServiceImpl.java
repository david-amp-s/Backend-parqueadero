package com.parqueadero.parkplace.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.parqueadero.parkplace.Service.ClienteService;
import com.parqueadero.parkplace.dto.ClienteCreateDto;
import com.parqueadero.parkplace.dto.ClienteDto;
import com.parqueadero.parkplace.exception.ClienteNoEncontradoException;
import com.parqueadero.parkplace.model.Cliente;
import com.parqueadero.parkplace.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;

    @Override
    public ClienteDto crear(ClienteCreateDto dto) {
        Cliente cliente = Cliente.builder()
                .nombre(dto.nombre())
                .correo(dto.correo())
                .cedula(dto.cedula())
                .build();
        clienteRepository.save(cliente);
        return new ClienteDto(cliente.getId(), cliente.getNombre(), cliente.getCorreo(), cliente.getCedula());
    }

    @Override
    public List<ClienteDto> listar() {
        return clienteRepository.findAll().stream()
                .map(c -> new ClienteDto(c.getId(), c.getNombre(), c.getCorreo(), c.getCedula()))
                .toList();
    }

    @Override
    public ClienteDto buscar(String cedula) {
        Cliente cliente = clienteRepository.findByCedula(cedula)
                .orElseThrow(() -> new ClienteNoEncontradoException(cedula));
        return new ClienteDto(cliente.getId(), cliente.getNombre(), cliente.getCorreo(), cliente.getCedula());
    }

    @Override
    public ClienteDto actualizar(String cedula, ClienteCreateDto dto) {
        Cliente cliente = clienteRepository.findByCedula(cedula)
                .orElseThrow(() -> new ClienteNoEncontradoException(cedula));
        cliente.setNombre(dto.nombre());
        cliente.setCedula(dto.cedula());
        cliente.setCorreo(dto.correo());
        clienteRepository.save(cliente);
        return new ClienteDto(cliente.getId(), cliente.getNombre(), cliente.getCorreo(), cliente.getCedula());
    }

    @Override
    public void eliminar(String cedula) {
        if (!clienteRepository.existsByCedula(cedula)) {
            throw new RuntimeException("El cliente no existe");
        }
        clienteRepository.deleteByCedula(cedula);
    }
}
