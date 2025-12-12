package com.parqueadero.parkplace.controller;

import org.springframework.web.bind.annotation.*;

import com.parqueadero.parkplace.Service.VehiculoService;
import com.parqueadero.parkplace.dto.VehiculoCreateDto;
import com.parqueadero.parkplace.dto.VehiculoDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

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

    @GetMapping("/id/{id}")
    public VehiculoDto buscarVehiculoId(@PathVariable Long id) {
        return vehiculoService.buscarPorId(id);
    }

    @GetMapping("/{placa}")
    public VehiculoDto buscarVehiculo(@PathVariable String placa) {
        return vehiculoService.buscarPorPlaca(placa);
    }

    @PutMapping("/{id}")
    public VehiculoDto corregirVehiculo(@PathVariable Long id, @RequestBody VehiculoCreateDto dto) {

        return vehiculoService.corregirVehiculo(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminarVehiculo(@PathVariable Long id){
        vehiculoService.eliminarVehiculo(id);
    }
}
