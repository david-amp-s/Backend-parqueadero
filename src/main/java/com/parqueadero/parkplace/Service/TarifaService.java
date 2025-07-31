package com.parqueadero.parkplace.Service;

import com.parqueadero.parkplace.dto.TarifaCreateDto;
import com.parqueadero.parkplace.dto.TarifaDto;

public interface TarifaService {
    TarifaDto cambiarTarifaMinuto(TarifaCreateDto dto);

    TarifaDto cambiarTarifaHora(TarifaCreateDto dto);

    TarifaDto cambiarTarifaFija(TarifaCreateDto dto);

}
