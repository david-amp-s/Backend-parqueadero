package com.parqueadero.parkplace.Service;

import java.time.LocalDateTime;
import java.util.List;

import com.parqueadero.parkplace.dto.IngresoCreateDto;
import com.parqueadero.parkplace.dto.IngresoDto;

public interface IngresoService {
    IngresoDto registrarIngreso(IngresoCreateDto dto);

    IngresoDto obteneIngresoPorId(Long id);

    List<IngresoDto> ListarTodosLosIngresos();

    void eliminarIngreso(Long id);

    List<IngresoDto> buscarPorFecha(LocalDateTime fecha);

    List<IngresoDto> buscarPorPlaca(String placa);

}
