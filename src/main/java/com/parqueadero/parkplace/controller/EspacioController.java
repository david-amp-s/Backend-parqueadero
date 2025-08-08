package com.parqueadero.parkplace.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parqueadero.parkplace.Service.EspacioService;
import com.parqueadero.parkplace.dto.EspacioCreateDto;
import com.parqueadero.parkplace.dto.EspacioDto;
import com.parqueadero.parkplace.enums.TipoVehiculo;
import com.parqueadero.parkplace.model.Espacio;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/espacios")
@RequiredArgsConstructor
public class EspacioController {
    private final EspacioService espacioService;

    @PostMapping()
    public EspacioDto crearEspacio(@RequestBody @Valid EspacioCreateDto dto) {
        return espacioService.crearEspacio(dto);
    }

    @GetMapping("/carro")
    public List<Espacio> espaciosDisponiblesCarro() {
        return espacioService.espaciosDisponiblesCarro();
    }

    @GetMapping("/moto")
    public List<Espacio> espaciosDisponiblesMoto() {
        return espacioService.espaciosDisponiblesMoto();
    }

    @GetMapping("/bicicleta")
    public List<Espacio> espaciosDisponiblesBicicleta() {
        return espacioService.espaciosDisponiblesBicicleta();
    }

    @PutMapping("/asignar")
    EspacioDto asignarEspacio(@RequestBody TipoVehiculo tipoVehiculo) {

        return espacioService.asignarEspacio(tipoVehiculo);
    }

    @PutMapping("{codigo}/liberar")
    public EspacioDto liberarEspacio(@PathVariable String codigo) {
        return espacioService.liberarEspacio(codigo);
    }
}
