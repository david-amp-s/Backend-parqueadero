package com.parqueadero.parkplace.Service;

import java.util.List;

import com.parqueadero.parkplace.dto.ClienteCreateDto;
import com.parqueadero.parkplace.dto.ClienteDto;
import com.parqueadero.parkplace.dto.InfoClienteDto;

public interface ClienteService {

    ClienteDto crear(ClienteCreateDto dto);

    List<ClienteDto> listar();

    ClienteDto buscar(String cedula);

    ClienteDto actualizar(String cedula, ClienteCreateDto dto);

    void eliminar(String cedula);

    InfoClienteDto infoClientes();
}
