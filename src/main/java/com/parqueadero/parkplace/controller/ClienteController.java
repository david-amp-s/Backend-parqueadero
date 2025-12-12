package com.parqueadero.parkplace.controller;

import org.springframework.web.bind.annotation.*;

import com.parqueadero.parkplace.Service.ClienteService;
import com.parqueadero.parkplace.dto.ClienteCreateDto;
import com.parqueadero.parkplace.dto.ClienteDto;
import com.parqueadero.parkplace.dto.InfoClienteDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @GetMapping("/{cedula}")
    public ClienteDto buscarCliente(@PathVariable String cedula) {
        return clienteService.buscar(cedula);
    }

    @GetMapping("/info")
    InfoClienteDto infoClientes() {
        return clienteService.infoClientes();
    }

    @GetMapping
    public List<ClienteDto> listar() {
        return clienteService.listar();
    }

    @PostMapping
    public ClienteDto crearCliente(@RequestBody @Valid ClienteCreateDto dto) {
        return clienteService.crear(dto);
    }

    @PutMapping("/{id}")
    public ClienteDto actualizar(@PathVariable Long id, @RequestBody @Valid ClienteCreateDto dto) {
        return clienteService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public  void eliminar(@PathVariable Long id){
        clienteService.eliminar(id);
    }

}
