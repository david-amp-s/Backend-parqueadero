package com.parqueadero.parkplace.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parqueadero.parkplace.Service.FacturaService;
import com.parqueadero.parkplace.dto.FacturaCreateDto;
import com.parqueadero.parkplace.dto.FacturaDto;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/factura")
@RequiredArgsConstructor
public class FacturaController {
    private final FacturaService facturaService;

    @PostMapping()
    public FacturaDto generarFactura(@RequestBody FacturaCreateDto dto) {

        return facturaService.generarFactura(dto);
    }

    @GetMapping("/{id}")
    public FacturaDto obtenerFactura(@PathVariable Long id) {
        return facturaService.obtenerFactura(id);
    }

    @GetMapping()
    public List<FacturaDto> listarTodasFacturas() {
        return facturaService.listarTodasFacturas();
    }

    @GetMapping("/fecha")
    public List<FacturaDto> buscarFacturasPorFecha(@RequestParam("fecha") LocalDate fecha) {
        return facturaService.buscarFacturasPorFecha(fecha);
    }

    @GetMapping("/usuario/{id}")
    public List<FacturaDto> buscarFacturaPorUsuario(@PathVariable Long id) {
        return facturaService.buscarFacturaPorUsuario(id);
    }

}
