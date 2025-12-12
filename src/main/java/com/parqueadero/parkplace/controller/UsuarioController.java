package com.parqueadero.parkplace.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.parqueadero.parkplace.Service.UsuarioService;
import com.parqueadero.parkplace.dto.UsuarioCreateDto;
import com.parqueadero.parkplace.dto.UsuarioDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping
    public UsuarioDto crear(@RequestBody @Valid UsuarioCreateDto dto) {
        return usuarioService.crear(dto);
    }

    @GetMapping
    public List<UsuarioDto> listar() {
        return usuarioService.listar();
    }

    @PutMapping("/{id}")
    public UsuarioDto modificar(@PathVariable Long id, @RequestBody @Valid UsuarioCreateDto dto) {
        return usuarioService.modificar(id, dto);
    }

    @DeleteMapping("/{id}")
    void eliminarUsuario(@PathVariable Long id){
        usuarioService.eliminarUsuario(id);
    }
}
