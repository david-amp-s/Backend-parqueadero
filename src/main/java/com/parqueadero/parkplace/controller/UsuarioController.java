package com.parqueadero.parkplace.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parqueadero.parkplace.Service.UsuarioService;
import com.parqueadero.parkplace.dto.UsuarioCreateDto;
import com.parqueadero.parkplace.dto.UsuarioDto;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping
    public UsuarioDto crear(@RequestBody UsuarioCreateDto dto) {
        return usuarioService.crear(dto);
    }

    @GetMapping
    public List<UsuarioDto> listar() {
        return usuarioService.listar();
    }

    @PutMapping("/{correo}")
    public UsuarioDto modificar(@PathVariable String correo, @RequestBody UsuarioCreateDto dto) {
        return usuarioService.modificar(correo, dto);
    }

}
