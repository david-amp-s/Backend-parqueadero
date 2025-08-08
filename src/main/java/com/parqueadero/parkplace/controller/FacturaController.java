package com.parqueadero.parkplace.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parqueadero.parkplace.Service.FacturaService;
import com.parqueadero.parkplace.Service.util.FacturaPDFService;
import com.parqueadero.parkplace.dto.FacturaCreateDto;
import com.parqueadero.parkplace.dto.FacturaDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/factura")
@RequiredArgsConstructor
public class FacturaController {
    private final FacturaService facturaService;
    private final FacturaPDFService facturaPDFService;

    @PostMapping()
    public FacturaDto generarFactura(@RequestBody @Valid FacturaCreateDto dto) {

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

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> descargarFactura(@PathVariable Long id) throws IOException {

        FacturaDto facturaDto = facturaService.obtenerFactura(id);

        // 2. Generar PDF en memoria
        byte[] pdfBytes = facturaPDFService.generarFactura(facturaDto);

        // 3. Preparar respuesta HTTP para descarga
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=factura_" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}
