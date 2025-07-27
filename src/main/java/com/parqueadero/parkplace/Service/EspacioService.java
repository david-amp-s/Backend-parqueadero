package com.parqueadero.parkplace.Service;

import java.util.List;

import com.parqueadero.parkplace.dto.EspacioCreateDto;
import com.parqueadero.parkplace.dto.EspacioDto;
import com.parqueadero.parkplace.enums.EstadoEspacio;
import com.parqueadero.parkplace.model.Espacio;

public interface EspacioService {

    EspacioDto crearEspacio(EspacioCreateDto dto);

    List<Espacio> espaciosDisponiblesCarro();

    List<Espacio> espaciosDisponiblesMoto();

    List<Espacio> espaciosDisponiblesBicicleta();

    EstadoEspacio asignarEspacio();

    EstadoEspacio liberarEspacio();
}
