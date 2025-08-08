package com.parqueadero.parkplace.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parqueadero.parkplace.Service.VehiculoService;
import com.parqueadero.parkplace.dto.VehiculoCreateDto;
import com.parqueadero.parkplace.dto.VehiculoDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/vehiculos")
@RequiredArgsConstructor
public class VehiculoController {
    final private VehiculoService vehiculoService;

    @PostMapping
    public VehiculoDto crearVehiculo(@RequestBody @Valid VehiculoCreateDto dto) {
        return vehiculoService.crear(dto);
    }

    @GetMapping()
    public List<VehiculoDto> listar() {
        return vehiculoService.listar();
    }

    @GetMapping("/{placa}")
    public VehiculoDto buscarVehiculo(@PathVariable String placa) {
        return vehiculoService.buscarPorPlaca(placa);
    }

    @PutMapping("/{placa}")
    public VehiculoDto corregirVehiculo(@PathVariable String placa, @RequestBody VehiculoCreateDto dto) {

        return vehiculoService.corregirVehiculo(placa, dto);
    }
}
