package com.parqueadero.parkplace.Service;

import java.util.List;

import com.parqueadero.parkplace.dto.EspacioCreateDto;
import com.parqueadero.parkplace.dto.EspacioDto;

import com.parqueadero.parkplace.enums.TipoVehiculo;
import com.parqueadero.parkplace.model.Espacio;

public interface EspacioService {

    EspacioDto crearEspacio(EspacioCreateDto dto);

    List<Espacio> espaciosDisponiblesCarro();

    List<Espacio> espaciosDisponiblesMoto();

    List<Espacio> espaciosDisponiblesBicicleta();

    EspacioDto asignarEspacio(TipoVehiculo tipoVehiculo);

    public EspacioDto liberarEspacio(String codigo);
}
