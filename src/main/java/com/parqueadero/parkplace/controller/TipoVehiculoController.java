package com.parqueadero.parkplace.controller;

import java.util.List;

import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.parqueadero.parkplace.Service.TipoVehiculoService;
import com.parqueadero.parkplace.dto.TipoVehiculoCreateDto;
import com.parqueadero.parkplace.dto.TipoVehiculoDto;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tipovehiculo")
@RequiredArgsConstructor
public class TipoVehiculoController {
    private final TipoVehiculoService tipoVehiculoService;

    @PostMapping
    public TipoVehiculoDto crearTipoVehiculo(@RequestBody TipoVehiculoCreateDto tipo) {
        return tipoVehiculoService.crearTipoVehiculo(tipo);
    }

    @GetMapping
    public List<TipoVehiculoDto> listarVehiculos() {
        return tipoVehiculoService.obtenerTodosLosTiposVehiculos();
    }

    @GetMapping("/{id}")
    public TipoVehiculoDto obtenerTipoVehiculoPorId(@RequestParam Long id) {
        return tipoVehiculoService.obtenerTipoVehiculoPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarTipoVehiculo(@RequestParam Long id) {
        tipoVehiculoService.eliminarTipoVehiculo(id);
    }

}
