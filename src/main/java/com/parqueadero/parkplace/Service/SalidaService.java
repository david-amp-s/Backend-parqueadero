package com.parqueadero.parkplace.Service;

import java.util.List;

import com.parqueadero.parkplace.dto.SalidaCreateDto;
import com.parqueadero.parkplace.dto.SalidaDto;

public interface SalidaService {
    SalidaDto registrarSalida(SalidaCreateDto salida);

    SalidaDto obtenerSalidaPorId(Long id);

    List<SalidaDto> listarTodasSalidas();

}
