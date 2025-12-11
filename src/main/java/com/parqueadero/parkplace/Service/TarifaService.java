package com.parqueadero.parkplace.Service;

import java.util.List;

import com.parqueadero.parkplace.dto.TarifaCreateDto;
import com.parqueadero.parkplace.dto.TarifaDto;
import com.parqueadero.parkplace.dto.TarifaDto;

public interface TarifaService {
    TarifaDto crearTarifa(TarifaCreateDto dto);

    TarifaDto cambiarTarifaMinuto(TarifaCreateDto dto);

    TarifaDto cambiarTarifaFija(TarifaCreateDto dto);

    List<TarifaDto> obtenerTodasLasTarifas();

}
