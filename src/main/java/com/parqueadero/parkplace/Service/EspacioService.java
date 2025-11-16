package com.parqueadero.parkplace.Service;

import java.util.List;

import com.parqueadero.parkplace.dto.EspacioCreateDto;
import com.parqueadero.parkplace.dto.EspacioDto;

import com.parqueadero.parkplace.model.Espacio;

public interface EspacioService {

    EspacioDto crearEspacio(EspacioCreateDto dto);

    List<Espacio> espaciosDisponibles(String vehiculo);

    EspacioDto asignarEspacio(String vehiculo);

    public EspacioDto liberarEspacio(String codigo);
}
