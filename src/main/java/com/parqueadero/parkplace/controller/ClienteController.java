package com.parqueadero.parkplace.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parqueadero.parkplace.Service.ClienteService;
import com.parqueadero.parkplace.dto.ClienteCreateDto;
import com.parqueadero.parkplace.dto.ClienteDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @GetMapping("/{cedula}")
    public ClienteDto buscarCliente(@PathVariable String cedula) {
        return clienteService.buscar(cedula);
    }

    @GetMapping
    public List<ClienteDto> listar() {
        return clienteService.listar();
    }

    @PostMapping
    public ClienteDto crearCliente(@RequestBody @Valid ClienteCreateDto dto) {
        return clienteService.crear(dto);
    }

    @PutMapping("/{cedula}")
    public ClienteDto actualizar(@PathVariable String cedula, @RequestBody ClienteCreateDto dto) {
        return clienteService.actualizar(cedula, dto);
    }

}
