package com.parqueadero.parkplace.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parqueadero.parkplace.Service.DetallePagoService;
import com.parqueadero.parkplace.dto.DetallePagoCreateDto;
import com.parqueadero.parkplace.dto.DetallePagoDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/detalle_pagos")
@RequiredArgsConstructor
public class DetallePagoController {
    private final DetallePagoService detallePagoService;

    @PostMapping()
    public DetallePagoDto registrarDetallePago(@RequestBody @Valid DetallePagoCreateDto dto) {
        return detallePagoService.registrarDetallePago(dto);
    }

    @PostMapping("/varios_pagos")
    public List<DetallePagoDto> registrarVariosPagos(@RequestBody @Valid List<DetallePagoCreateDto> pagos) {
        return detallePagoService.registrarVariosPagos(pagos);
    }

    @GetMapping("/pagos/{facturaId}")
    public List<DetallePagoDto> obtenerPagosPorFactura(@PathVariable Long facturaId) {
        return detallePagoService.obtenerPagosPorFactura(facturaId);
    }

    @GetMapping("/detalle/{id}")
    public DetallePagoDto obtenerDetallePago(@PathVariable Long id) {
        return detallePagoService.obtenerDetallePago(id);
    }

}
