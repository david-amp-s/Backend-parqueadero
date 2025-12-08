package com.parqueadero.parkplace.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parqueadero.parkplace.Service.TarifaService;
import com.parqueadero.parkplace.dto.TarifaCreateDto;
import com.parqueadero.parkplace.dto.TarifaDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/tarifas")
@RequiredArgsConstructor
public class TarifaController {

    private final TarifaService tarifaService;

    @PostMapping()
    public TarifaDto crearTarifa(@RequestBody TarifaCreateDto dto) {
        return tarifaService.crearTarifa(dto);
    }

    @GetMapping()
    public List<TarifaDto> listarTarifas() {
        return tarifaService.obtenerTodasLasTarifas();
    }

    @PutMapping("/minuto")
    public TarifaDto cambiarTarifaMinuto(@RequestBody @Valid TarifaCreateDto dto) {
        return tarifaService.cambiarTarifaMinuto(dto);
    }

    @PutMapping("/fija")
    public TarifaDto cambiarTarifaFija(@RequestBody @Valid TarifaCreateDto dto) {
        return tarifaService.cambiarTarifaFija(dto);
    }
}
