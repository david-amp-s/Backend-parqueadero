package com.parqueadero.parkplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parqueadero.parkplace.model.Tarifa;
import com.parqueadero.parkplace.enums.TipoVehiculo;

public interface TarifaRepository extends JpaRepository<Tarifa, Long> {
    Tarifa findByTipoVehiculo(TipoVehiculo tipoVehiculo);

}
