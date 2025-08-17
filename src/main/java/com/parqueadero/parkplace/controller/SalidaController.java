package com.parqueadero.parkplace.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parqueadero.parkplace.Service.SalidaService;
import com.parqueadero.parkplace.dto.SalidaCreateDto;
import com.parqueadero.parkplace.dto.SalidaDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/salidas")
@RequiredArgsConstructor
public class SalidaController {
    private final SalidaService salidaService;

    @PostMapping()
    public SalidaDto registrarSalida(@RequestBody @Valid SalidaCreateDto salida) {
        return salidaService.registrarSalida(salida);
    }

    @GetMapping("/{id}")
    SalidaDto obtenerSalidaPorId(@PathVariable Long id) {
        return salidaService.obtenerSalidaPorId(id);
    }

    @GetMapping()
    public List<SalidaDto> listarTodasSalidas() {
        return salidaService.listarTodasSalidas();
    }

    @DeleteMapping("/{placa}")
    public ResponseEntity<Void> cancelarSalida(@PathVariable String placa) {
        salidaService.cancelarSalida(placa);
        return ResponseEntity.noContent().build();
    }
}
