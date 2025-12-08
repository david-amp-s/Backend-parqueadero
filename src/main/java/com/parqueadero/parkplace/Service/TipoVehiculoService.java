package com.parqueadero.parkplace.Service;

import java.util.List;

import com.parqueadero.parkplace.dto.TipoVehiculoCreateDto;
import com.parqueadero.parkplace.dto.TipoVehiculoDto;

public interface TipoVehiculoService {
    TipoVehiculoDto crearTipoVehiculo(TipoVehiculoCreateDto dto);

    TipoVehiculoDto obtenerTipoVehiculoPorId(Long id);

    List<TipoVehiculoDto> obtenerTodosLosTiposVehiculos();

    void eliminarTipoVehiculo(Long id);
}
