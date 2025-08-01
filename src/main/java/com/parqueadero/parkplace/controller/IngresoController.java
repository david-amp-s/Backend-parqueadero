package com.parqueadero.parkplace.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parqueadero.parkplace.Service.IngresoService;
import com.parqueadero.parkplace.dto.IngresoCreateDto;
import com.parqueadero.parkplace.dto.IngresoDto;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/ingreso")
@RequiredArgsConstructor
public class IngresoController {
    private final IngresoService ingresoService;

    @PostMapping()
    public IngresoDto registrarIngreso(@RequestBody IngresoCreateDto dto) {
        return ingresoService.registrarIngreso(dto);
    }

    @GetMapping("/{id}")
    public IngresoDto obteneIngresoPorId(@PathVariable Long id) {
        return ingresoService.obteneIngresoPorId(id);
    }

    @GetMapping()
    List<IngresoDto> ListarTodosLosIngresos() {
        return ingresoService.ListarTodosLosIngresos();
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarIngreso(@PathVariable Long id) {
        ingresoService.eliminarIngreso(id);
    }

    @PostMapping("/fecha")
    public List<IngresoDto> buscarPorFecha(@RequestBody LocalDateTime fecha) {
        return ingresoService.buscarPorFecha(fecha);
    }

    @PostMapping("/placa")
    public List<IngresoDto> buscarPorPlaca(@RequestBody String placa) {
        return ingresoService.buscarPorPlaca(placa);
    }
}
