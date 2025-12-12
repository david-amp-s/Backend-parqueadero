package com.parqueadero.parkplace.Service;

import java.util.List;

import com.parqueadero.parkplace.dto.InfoClienteDto;
import com.parqueadero.parkplace.dto.UsuarioCreateDto;
import com.parqueadero.parkplace.dto.UsuarioDto;

public interface UsuarioService {
    UsuarioDto crear(UsuarioCreateDto dto);

    List<UsuarioDto> listar();

    UsuarioDto buscar(String correo);

    UsuarioDto modificar(Long id, UsuarioCreateDto dto);

    void eliminarUsuario(Long id);

}
