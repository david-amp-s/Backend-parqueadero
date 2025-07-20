package com.parqueadero.parkplace.Service;

import java.util.List;

import com.parqueadero.parkplace.dto.VehiculoCreateDto;
import com.parqueadero.parkplace.dto.VehiculoDto;

public interface VehiculoService {
    VehiculoDto crear(VehiculoCreateDto dto);

    List<VehiculoDto> listar();

    VehiculoDto buscarPorPlaca(String placa);

    VehiculoDto corregirVehiculo(String placa, VehiculoCreateDto dto);

    void eliminarVehiculo(String placa);
}
