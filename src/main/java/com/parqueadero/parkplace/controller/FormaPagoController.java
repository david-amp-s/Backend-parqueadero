package com.parqueadero.parkplace.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parqueadero.parkplace.Service.FormaPagoService;
import com.parqueadero.parkplace.dto.FormaPagoCreateDto;
import com.parqueadero.parkplace.dto.FormaPagoDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/formas_pago")
@RequiredArgsConstructor
public class FormaPagoController {
    private final FormaPagoService formaPagoService;

    @PostMapping()
    public FormaPagoDto ingresarFormaPago(@RequestBody @Valid FormaPagoCreateDto dto) {
        return formaPagoService.ingresarFormaPago(dto);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarFormaPago(@PathVariable Long id) {
        formaPagoService.eliminarFormaPago(id);
    }

    @GetMapping()
    public List<FormaPagoDto> listaFormaPago() {
        return formaPagoService.listaFormaPago();
    }

}
