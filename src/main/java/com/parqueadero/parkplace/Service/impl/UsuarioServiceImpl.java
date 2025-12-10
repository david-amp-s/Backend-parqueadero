package com.parqueadero.parkplace.Service.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.parqueadero.parkplace.Service.UsuarioService;
import com.parqueadero.parkplace.dto.InfoClienteDto;
import com.parqueadero.parkplace.dto.UsuarioCreateDto;
import com.parqueadero.parkplace.dto.UsuarioDto;
import com.parqueadero.parkplace.exception.RolNoEncontrado;
import com.parqueadero.parkplace.exception.UsuarioNoEncontrado;
import com.parqueadero.parkplace.model.Rol;
import com.parqueadero.parkplace.model.Usuario;
import com.parqueadero.parkplace.repository.RolRepository;
import com.parqueadero.parkplace.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    private final PasswordEncoder passwordEncoder;

    private Rol obtenerRol(String nombre) {
        Rol rol = rolRepository.findByNombre(nombre)
                .orElseThrow(() -> new RolNoEncontrado(nombre));
        return rol;
    }

    private String deserealizarRol(Rol rol) {
        return rol.getNombre();
    }

    private UsuarioDto convertirDto(Usuario usuario) {
        return new UsuarioDto(usuario.getId(), usuario.getNombre(), usuario.getEmail(),
                deserealizarRol(usuario.getRol()));
    }

    @Override
    public UsuarioDto crear(UsuarioCreateDto dto) {
        Usuario usuario = Usuario.builder()
                .nombre(dto.nombre())
                .email(dto.email())
                .contraseña(passwordEncoder.encode(dto.contraseña()))
                .rol(obtenerRol(dto.rolNombre()))
                .build();
        usuarioRepository.save(usuario);
        return convertirDto(usuario);
    }

    @Override
    public List<UsuarioDto> listar() {
        return usuarioRepository.findAll().stream()
                .map(u -> convertirDto(u)).toList();
    }

    @Override
    public UsuarioDto buscar(String correo) {
        Usuario usuario = usuarioRepository.findByEmail(correo).orElseThrow(() -> new UsuarioNoEncontrado());
        return convertirDto(usuario);
    }

    @Override
    public UsuarioDto modificar(String correo, UsuarioCreateDto dto) {
        Usuario usuario = usuarioRepository.findByEmail(correo)
                .orElseThrow(() -> new UsuarioNoEncontrado());
        usuario.setNombre(dto.nombre());
        usuario.setContraseña(dto.contraseña());
        usuario.setRol(obtenerRol(dto.rolNombre()));
        usuarioRepository.save(usuario);
        return convertirDto(usuario);
    }

}
