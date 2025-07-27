package com.parqueadero.parkplace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parqueadero.parkplace.enums.EstadoEspacio;
import com.parqueadero.parkplace.enums.TipoVehiculo;
import com.parqueadero.parkplace.model.Espacio;

public interface EspacioRepository extends JpaRepository<Espacio, Long> {
    List<Espacio> findByTipoEspacioAndTipoVehiculoPermitidoByIdAsc(
            EstadoEspacio tipoEspacio,
            TipoVehiculo tipoVehiculoPermitido);
}
